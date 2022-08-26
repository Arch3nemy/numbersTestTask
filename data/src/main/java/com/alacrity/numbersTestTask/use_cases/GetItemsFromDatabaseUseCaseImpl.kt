package com.alacrity.numbersTestTask.use_cases

import com.alacrity.numbersTestTask.Repository
import com.alacrity.numbersTestTask.entity.NumberWithFact
import javax.inject.Inject

class GetItemsFromDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetItemsFromDatabaseUseCase {

    override suspend fun invoke() = repository.getAllItemsFromDB()

}