package com.alacrity.numbersTestTask.di

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.alacrity.numbersTestTask.Repository
import com.alacrity.numbersTestTask.repository.RepositoryImpl
import com.alacrity.numbersTestTask.room.entity.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context.applicationContext

    @Provides
    fun provideResources(): Resources = context.resources

    @Singleton
    @Provides
    fun provideYourDatabase(
        app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "numbersWithFacts"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.numberWithFactDao()

}