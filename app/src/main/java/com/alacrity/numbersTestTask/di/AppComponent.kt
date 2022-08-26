package com.alacrity.numbersTestTask.di

import com.alacrity.numbersTestTask.App
import com.alacrity.numbersTestTask.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, UseCaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(app: App)

}