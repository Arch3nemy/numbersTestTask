package com.alacrity.numbersTestTask.view_states

sealed interface BaseViewState {

    fun getBaseState(): BaseViewState = Loading

    companion object {
        object Loading : BaseViewState
    }
}