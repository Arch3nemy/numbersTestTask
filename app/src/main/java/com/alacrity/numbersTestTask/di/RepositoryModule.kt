package com.alacrity.numbersTestTask.di

import com.alacrity.numbersTestTask.Repository
import com.alacrity.numbersTestTask.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImpl): Repository

}