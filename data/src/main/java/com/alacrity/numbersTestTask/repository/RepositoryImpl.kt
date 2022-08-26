package com.alacrity.numbersTestTask.repository

import com.alacrity.numbersTestTask.Repository
import com.alacrity.numbersTestTask.entity.NumberWithFact
import com.alacrity.numbersTestTask.exceptions.NoDataFromResponseException
import com.alacrity.numbersTestTask.exceptions.UnableToRetrieveNumberFromRandomFactException
import com.alacrity.numbersTestTask.room.entity.AppDatabase
import com.alacrity.numbersTestTask.room.entity.toRawItems
import com.alacrity.numbersTestTask.utils.getFirstNumberFromString
import com.alacrity.numbersTestTask.retrofit.Api
import com.alacrity.numbersTestTask.utils.toTableItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RepositoryImpl @Inject constructor(
    private val retrofit: Retrofit,
    private val db: AppDatabase
): Repository {

    override suspend fun getFact(number: Int?): NumberWithFact {
        val api = retrofit.create(Api::class.java)
        val call = if(number == null) api.getRandomFactAboutNumber() else api.getFactAboutNumber(number)
        return suspendCoroutine { continuation ->
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val data = response.body()?.string()
                    data ?: continuation.resumeWithException(NoDataFromResponseException())
                        .also { return }

                    val numberFromString = data?.getFirstNumberFromString()

                    numberFromString ?: continuation.resumeWithException(
                        UnableToRetrieveNumberFromRandomFactException()
                    )
                    val uid = UUID.randomUUID().toString()
                    continuation.resume(NumberWithFact(uid, number ?: numberFromString!!, data!!))
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    override suspend fun saveItemToDB(item: NumberWithFact) {
        db.numberWithFactDao().insertAll(item.toTableItem())
    }

    override suspend fun getAllItemsFromDB(): List<NumberWithFact> = db.numberWithFactDao().getAll().toRawItems()

    override suspend fun removeItemFromDB(numberWithFact: NumberWithFact) = db.numberWithFactDao().delete(numberWithFact.toTableItem())
}