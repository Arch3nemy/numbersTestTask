package com.alacrity.numbersTestTask

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alacrity.numbersTestTask.Destinations.HOME_ROUTE
import com.alacrity.numbersTestTask.ui.main.MainViewModel

@Composable
fun AppNavGraph(
    homeViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(HOME_ROUTE) {
            MainScreen(
                viewModel = homeViewModel,
            )
        }
    }
}
