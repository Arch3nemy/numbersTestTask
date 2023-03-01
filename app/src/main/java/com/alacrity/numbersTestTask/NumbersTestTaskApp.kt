package com.alacrity.numbersTestTask

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.alacrity.numbersTestTask.theme.AppTheme
import com.alacrity.numbersTestTask.ui.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

object Destinations {
    const val HOME_ROUTE = "home"
}

@Composable
fun NumbersTestTaskApp(
    homeViewModel: MainViewModel
) {
    AppTheme {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }

            AppNavGraph(
                homeViewModel = homeViewModel,
            )
        }

}

