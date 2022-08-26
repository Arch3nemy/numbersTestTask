package com.alacrity.numbersTestTask.view_states

import com.alacrity.numbersTestTask.entity.NumberWithFact


sealed class MainViewState: BaseViewState {
    object InitialState : MainViewState()
    object Loading : MainViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : MainViewState()
    object FinishedLoading : MainViewState()

    data class NumberDetails(val numberWithFact: NumberWithFact): MainViewState()
}