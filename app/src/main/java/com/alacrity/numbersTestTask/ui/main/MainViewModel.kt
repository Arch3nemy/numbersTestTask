package com.alacrity.numbersTestTask.ui.main

import com.alacrity.numbersTestTask.entity.NumberWithFact
import com.alacrity.numbersTestTask.room.entity.NumberWithFactTableItem
import com.alacrity.numbersTestTask.room.entity.toRawItems
import com.alacrity.numbersTestTask.ui.main.models.MainEvent
import com.alacrity.numbersTestTask.use_cases.*
import com.alacrity.numbersTestTask.util.BaseViewModel
import com.alacrity.numbersTestTask.utils.toTableItems
import com.alacrity.numbersTestTask.view_states.MainViewState
import com.alacrity.numbersTestTask.view_states.MainViewState.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getFactAboutNumberUseCase: GetFactAboutNumberUseCase,
    private val saveItemToDatabaseUseCase: SaveItemToDatabaseUseCase,
    private val getItemsFromDatabaseUseCase: GetItemsFromDatabaseUseCase,
    private val removeItemFromDatabaseUseCase: RemoveItemFromDatabaseUseCase
) : BaseViewModel<MainEvent, MainViewState>(InitialState) {

    val viewState: StateFlow<MainViewState> = _viewState

    val savedNumbersWithFactsState: MutableSharedFlow<MutableList<NumberWithFact>> =
        MutableSharedFlow()

    private val list = mutableSetOf<NumberWithFact>()

    init {
        launch {
            savedNumbersWithFactsState.collect {
                list.addAll(it)
            }
        }
    }


    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is InitialState -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is FinishedLoading -> currentState.reduce(event)
            is NumberDetails -> currentState.reduce(event)
        }
    }

    private fun InitialState.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterScreen -> {
                loadItemsFromDatabase()
            }
            else -> Unit
        }
    }

    private fun FinishedLoading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            is MainEvent.EnterSecondScreen -> {
                if(event.numberWithFact == null)
                    loadDataForNumber(event.number)
                else
                    _viewState.value = NumberDetails(event.numberWithFact)
            }

            is MainEvent.RemoveNumberWithFact -> {
                val item = event.numberWithFact
                removeNumberWithFact(item)
            }

            else -> Unit
        }
    }

    private fun NumberDetails.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterFirstScreen -> {
                _viewState.value = FinishedLoading
            }
            else -> Unit
        }

    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun loadDataForNumber(number: Int?) {
        _viewState.value = Loading
        launch(
            logError = "Error retrieving fact about number $number",
            logSuccess = "Successfully retrieved fact about number $number",
            onSuccess = {
                saveItemToDatabase(it)
                doThenEmitChanges { list.add(it) }
            },
            onFailure = {
                _viewState.value = Error(it)
            }) {
                getFactAboutNumberUseCase(number)
        }
    }

    private fun doThenEmitChanges(block: () -> Unit) {
        launch {
            block()
            savedNumbersWithFactsState.emit(list.toMutableList())
        }

    }

    private fun saveItemToDatabase(item: NumberWithFact) {
        launch(
            logError = "Error saving data to db $item",
            logSuccess = "Successfully saved item to db $item",
            onSuccess = {
                _viewState.value = NumberDetails(item)
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            saveItemToDatabaseUseCase(item)
        }
    }

    private fun loadItemsFromDatabase() {
        launch(
            logError = "Error saving data to db",
            logSuccess = "Successfully saved item to db",
        ) {
            val items = getItemsFromDatabaseUseCase()
            emitItems(items.toTableItems())
        }
    }

    private fun emitItems(items: List<NumberWithFactTableItem>) {
        launch(
            logSuccess = "Successfully emitted items to shared flow",
            logError = "Error emitting items to shared flow",
            onSuccess = {
                _viewState.value = FinishedLoading
            },
            onFailure = {
                _viewState.value = Error(it)
            }
        ) {
            savedNumbersWithFactsState.emit(items.toRawItems())
        }
    }

    private fun removeNumberWithFact(numberWithFact: NumberWithFact) {
        launch(
            onSuccess = {
                doThenEmitChanges { list.remove(numberWithFact) }
            }
        ) {
            removeItemFromDatabaseUseCase(numberWithFact)
        }

    }
}