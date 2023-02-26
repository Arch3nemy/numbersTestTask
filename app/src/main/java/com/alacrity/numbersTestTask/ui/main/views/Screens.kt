package com.alacrity.numbersTestTask.ui.main.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alacrity.numbersTestTask.entity.NumberWithFact
import com.alacrity.numbersTestTask.theme.BrightGray

@Composable
fun FirstScreen(
    list: List<NumberWithFact>, onGetFactClicked: (Int) -> Unit,
    onGetRandomFactClicked: () -> Unit,
    onItemClick: (NumberWithFact) -> Unit,
    onLongItemClick: (NumberWithFact) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrightGray),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TopSection(
            modifier = Modifier.weight(1f),
            onGetFactClicked = onGetFactClicked,
            onGetRandomFactClicked = onGetRandomFactClicked
        )

        Divider(color = Color.Black, thickness = 1.dp)

        BottomSection(
            modifier = Modifier.weight(1f),
            list,
            onItemClick = { onItemClick(it) },
            onLongItemClick = { onLongItemClick(it) }
        )
    }
}

@Composable
fun SecondScreen(numberWithFact: NumberWithFact, onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrightGray)
    ) {
        RowWithBackButton(onBackPressed)
        numberWithFact.number?.let { TopSection(number = it) }
        BottomSection(fact = numberWithFact.fact)
    }
    BackHandler {
        onBackPressed()
    }
}

