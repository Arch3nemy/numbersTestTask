package com.alacrity.numbersTestTask.use_cases

import com.alacrity.numbersTestTask.entity.NumberWithFact


interface SaveItemToDatabaseUseCase {

    suspend operator fun invoke(item: NumberWithFact)

}