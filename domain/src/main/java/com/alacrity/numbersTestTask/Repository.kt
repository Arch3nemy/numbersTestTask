package com.alacrity.numbersTestTask

import com.alacrity.numbersTestTask.entity.NumberWithFact

interface Repository {

    suspend fun getFact(number: Int?): NumberWithFact

    suspend fun saveItemToDB(item: NumberWithFact)

    suspend fun getAllItemsFromDB(): List<NumberWithFact>

    suspend fun removeItemFromDB(item: NumberWithFact)

}