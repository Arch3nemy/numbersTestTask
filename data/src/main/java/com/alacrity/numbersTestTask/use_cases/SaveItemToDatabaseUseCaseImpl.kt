package com.alacrity.numbersTestTask.use_cases

import com.alacrity.numbersTestTask.Repository
import com.alacrity.numbersTestTask.entity.NumberWithFact
import javax.inject.Inject

class SaveItemToDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
): SaveItemToDatabaseUseCase {

    override suspend fun invoke(item: NumberWithFact) = repository.saveItemToDB(item)

}