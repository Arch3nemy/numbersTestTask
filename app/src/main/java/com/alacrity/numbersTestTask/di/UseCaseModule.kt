package com.alacrity.numbersTestTask.di


import com.alacrity.numbersTestTask.use_cases.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UseCaseModule {

    @Binds
    fun bindGetFactAboutNumberUseCaseImpl(impl: GetFactAboutNumberUseCaseImpl): GetFactAboutNumberUseCase

    @Binds
    fun bindSaveItemToDatabaseUseCaseImpl(impl: SaveItemToDatabaseUseCaseImpl): SaveItemToDatabaseUseCase

    @Binds
    fun bindGetItemsFromDatabaseUseCaseImpl(impl: GetItemsFromDatabaseUseCaseImpl): GetItemsFromDatabaseUseCase

    @Binds
    fun bindRemoveItemFromDatabaseUseCase(impl: RemoveItemFromDatabaseUseCaseImpl): RemoveItemFromDatabaseUseCase
}