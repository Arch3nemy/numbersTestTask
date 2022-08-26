package com.alacrity.numbersTestTask.di


import com.alacrity.numbersTestTask.use_cases.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindGetFactAboutNumberUseCaseImpl(impl: GetFactAboutNumberUseCaseImpl): GetFactAboutNumberUseCase

    @Binds
    @Singleton
    fun bindSaveItemToDatabaseUseCaseImpl(impl: SaveItemToDatabaseUseCaseImpl): SaveItemToDatabaseUseCase

    @Binds
    @Singleton
    fun bindGetItemsFromDatabaseUseCaseImpl(impl: GetItemsFromDatabaseUseCaseImpl): GetItemsFromDatabaseUseCase

    @Binds
    @Singleton
    fun bindRemoveItemFromDatabaseUseCase(impl: RemoveItemFromDatabaseUseCaseImpl): RemoveItemFromDatabaseUseCase
}