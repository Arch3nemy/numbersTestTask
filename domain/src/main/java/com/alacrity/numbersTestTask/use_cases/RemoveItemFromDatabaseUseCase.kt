package com.alacrity.numbersTestTask.use_cases

import com.alacrity.numbersTestTask.entity.NumberWithFact

interface RemoveItemFromDatabaseUseCase {

    suspend operator fun invoke(item: NumberWithFact)

}