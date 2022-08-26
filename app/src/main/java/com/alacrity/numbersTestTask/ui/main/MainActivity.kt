package com.alacrity.numbersTestTask.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.alacrity.numbersTestTask.App
import com.alacrity.numbersTestTask.NumbersTestTaskApp
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContent {
            NumbersTestTaskApp(context = this, homeViewModel = mainViewModel)
        }
    }

}