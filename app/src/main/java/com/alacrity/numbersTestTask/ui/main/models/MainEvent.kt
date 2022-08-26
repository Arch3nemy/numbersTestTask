package com.alacrity.numbersTestTask.ui.main.models

import com.alacrity.numbersTestTask.BaseEvent
import com.alacrity.numbersTestTask.entity.NumberWithFact
import com.alacrity.numbersTestTask.ui.main.MainViewModel

sealed class MainEvent: BaseEvent {

    object EnterScreen : MainEvent()

    data class EnterSecondScreen(val number: Int? = null, val numberWithFact: NumberWithFact? = null) : MainEvent()

    data class RemoveNumberWithFact(val numberWithFact: NumberWithFact): MainEvent()

    object EnterFirstScreen: MainEvent()

}

fun MainViewModel.enterScreen() {
    obtainEvent(MainEvent.EnterScreen)
}

fun MainViewModel.enterSecondScreenWithFactForNumber(number: Int) {
    obtainEvent(MainEvent.EnterSecondScreen(number))
}

fun MainViewModel.enterSecondScreenWithGivenFact(numberWithFact: NumberWithFact) {
    obtainEvent(MainEvent.EnterSecondScreen(numberWithFact = numberWithFact))
}

fun MainViewModel.removeNumberWithFact(numberWithFact: NumberWithFact) {
    obtainEvent(MainEvent.RemoveNumberWithFact(numberWithFact = numberWithFact))
}

fun MainViewModel.enterFirstScreen() {
    obtainEvent(MainEvent.EnterFirstScreen)
}

fun MainViewModel.enterSecondScreenWithRandomFact() {
    obtainEvent(MainEvent.EnterSecondScreen(null))
}




