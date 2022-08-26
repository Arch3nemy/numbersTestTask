package com.alacrity.numbersTestTask.use_cases

import com.alacrity.numbersTestTask.entity.NumberWithFact

interface GetItemsFromDatabaseUseCase {

    suspend operator fun invoke(): List<NumberWithFact>

}