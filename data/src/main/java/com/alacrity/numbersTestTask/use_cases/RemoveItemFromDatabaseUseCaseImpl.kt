package com.alacrity.numbersTestTask.use_cases

import com.alacrity.numbersTestTask.Repository
import com.alacrity.numbersTestTask.entity.NumberWithFact
import javax.inject.Inject

class RemoveItemFromDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
) : RemoveItemFromDatabaseUseCase {

    override suspend fun invoke(item: NumberWithFact) = repository.removeItemFromDB(item)

}