package com.alacrity.numbersTestTask

import android.app.Application
import com.alacrity.numbersTestTask.di.ApiModule
import com.alacrity.numbersTestTask.di.AppComponent
import com.alacrity.numbersTestTask.di.AppModule
import com.alacrity.numbersTestTask.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent
            .builder()
            .apiModule(ApiModule("http://numbersapi.com/"))
            .appModule(AppModule(this))
            .build()
            .apply { inject(this@App) }
    }
}