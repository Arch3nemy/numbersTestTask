package com.alacrity.numbersTestTask.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/{number}")
    fun getFactAboutNumber(@Path("number") number: Int): Call<ResponseBody>

    @GET("/random")
    fun getRandomFactAboutNumber(): Call<ResponseBody>

}