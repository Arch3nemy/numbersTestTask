package com.alacrity.numbersTestTask.use_cases

import com.alacrity.numbersTestTask.entity.NumberWithFact

interface GetFactAboutNumberUseCase {

    suspend operator fun invoke(number: Int?): NumberWithFact

}