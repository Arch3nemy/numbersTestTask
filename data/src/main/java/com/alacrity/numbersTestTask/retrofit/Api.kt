package com.alacrity.numbersTestTask.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/{number}")
    suspend fun getFactAboutNumber(@Path("number") number: Int): String

    @GET("/random")
    suspend fun getRandomFactAboutNumber(): String

}