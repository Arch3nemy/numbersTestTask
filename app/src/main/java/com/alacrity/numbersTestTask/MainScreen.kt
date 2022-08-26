package com.alacrity.numbersTestTask

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alacrity.numbersTestTask.ui.main.MainViewModel
import com.alacrity.numbersTestTask.ui.main.models.*
import com.alacrity.numbersTestTask.ui.main.views.FirstScreen
import com.alacrity.numbersTestTask.ui.main.views.SecondScreen
import com.alacrity.numbersTestTask.view_states.MainViewState

@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {

    val state by viewModel.viewState.collectAsState()
    val savedItems by viewModel.savedNumbersWithFactsState.collectAsState(initial = emptyList())

    when (state) {
        MainViewState.InitialState -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LinearProgressIndicator()
            }
        }

        MainViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LinearProgressIndicator()
            }
        }

        is MainViewState.FinishedLoading -> {
            FirstScreen(
                list = savedItems,
                onGetFactClicked = { viewModel.enterSecondScreenWithFactForNumber(it) },
                onGetRandomFactClicked = { viewModel.enterSecondScreenWithRandomFact() },
                onItemClick = { viewModel.enterSecondScreenWithGivenFact(it) },
            onLongItemClick = { viewModel.removeNumberWithFact(it) })
        }

        is MainViewState.NumberDetails -> {
            SecondScreen(
                numberWithFact = (state as MainViewState.NumberDetails).numberWithFact,
                onBackPressed = { viewModel.enterFirstScreen() }
            )
        }

        is MainViewState.Error -> {
            /* ShowErrorView */
        }

        else -> Unit
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })
}