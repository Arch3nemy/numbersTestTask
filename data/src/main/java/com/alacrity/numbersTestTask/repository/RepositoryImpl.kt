package com.alacrity.numbersTestTask.repository

import com.alacrity.numbersTestTask.Repository
import com.alacrity.numbersTestTask.entity.NumberWithFact
import com.alacrity.numbersTestTask.retrofit.Api
import com.alacrity.numbersTestTask.room.entity.AppDatabase
import com.alacrity.numbersTestTask.room.entity.NumberWithFactTableItem
import java.util.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val api: Api
) : Repository {
    override suspend fun getFact(number: Int?): NumberWithFact {
        val result = if (number == null)  api.getRandomFactAboutNumber() else api.getFactAboutNumber(number)
        return NumberWithFact(UUID.randomUUID().toString(), number, result)
    }

    override suspend fun saveItemToDB(item: NumberWithFact) {
        db.numberWithFactDao().insertAll(item.let { NumberWithFactTableItem(it.uid, it.number, it.fact) })
    }

    override suspend fun getAllItemsFromDB(): List<NumberWithFact> =
        db.numberWithFactDao().getAll().map { NumberWithFact(it.uid, it.number, it.fact) }

    override suspend fun removeItemFromDB(item: NumberWithFact) =
        db.numberWithFactDao().delete(item.let { NumberWithFactTableItem(it.uid, it.number, it.fact) })
}