package com.alacrity.numbersTestTask.use_cases

import com.alacrity.numbersTestTask.Repository
import javax.inject.Inject

class GetFactAboutNumberUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetFactAboutNumberUseCase {

    override suspend fun invoke(number: Int?) = repository.getFact(number)

}