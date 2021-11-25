package com.onepercent.xweight.weight.ui_weightList.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onepercent.xweight.core.domain.DataState
import com.onepercent.xweight.core.domain.Queue
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement
import com.onepercent.xweight.weight.weight_interactors.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel
@Inject
constructor(
    private val insertWeightMeasurement: InsertWeightMeasurement,
    private val getAllMeasurements: GetAllMeasurements,
    private val getLastMeasurement: GetLastMeasurement,
    private val sortMeasurements: SortMeasurements,
    private val deleteMeasurement: DeleteMeasurement
) : ViewModel() {

    val state: MutableState<HistoryScreenState> = mutableStateOf(HistoryScreenState())

    init {
        onTriggerEvent(HistoryScreenEvent.GetAllMeasurements)
    }

    fun onTriggerEvent(event: HistoryScreenEvent) {
        when (event) {
            is HistoryScreenEvent.GetAllMeasurements -> {
                getMeasurements()
            }
            is HistoryScreenEvent.GetLastMeasurement -> {
                getLastMeasurement()
            }

            is HistoryScreenEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            is HistoryScreenEvent.UpdateFabDialogState -> {
                state.value = state.value.copy(
                    fabDialogState = event.uiComponentState,
                    measurementDate = System.currentTimeMillis()
                )
            }

            is HistoryScreenEvent.UpdateEditMeasurementDialogState -> {
                state.value = state.value.copy(
                    editMeasurementDialogState = event.uiComponentState
                )
            }

            is HistoryScreenEvent.InsertWeightMeasurement -> {
                insertWeightMeasurement(event.weightMeasurement)
            }

            is HistoryScreenEvent.DeleteWeightMeasurement -> {
                deleteWeightMeasurement(event.date)
            }

            is HistoryScreenEvent.PickDateForNewMeasurement -> {
                state.value = state.value.copy(measurementDate = event.date)
            }
            is HistoryScreenEvent.PickValueForNewMeasurement -> {
                state.value = state.value.copy(measurementValue = event.weight)
            }
            is HistoryScreenEvent.PickMeasurementForEdit -> {
                state.value = state.value.copy(measurementDate = event.date, measurementValue = event.weight)
            }
        }
    }

    private fun insertWeightMeasurement(weightMeasurement: WeightMeasurement) {
        insertWeightMeasurement
            .execute(weightMeasurement.weight, weightMeasurement.date)
            .onEach { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        state.value = state.value.copy(progressBarState = dataState.progressBarState)
                    }
                    is DataState.Data -> {
                        getMeasurements()
                    }
                    is DataState.Response -> {
                        if (dataState.uiComponent is UIComponent.None) {
                            println("insertWeightMeasurement ${(dataState.uiComponent as UIComponent.None).message}")
                        } else{
                            appendToMessageQueue(dataState.uiComponent)
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getMeasurements() {
        getAllMeasurements.execute().onEach { dataState ->
            when(dataState){
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
                is DataState.Data -> {
                    state.value = state.value.copy(weightMeasurements = dataState.data?: listOf())
                    sortMeasurements()
                }
                is DataState.Response -> {
                    if(dataState.uiComponent is UIComponent.None){
                        println("getAllMeasurements: ${(dataState.uiComponent as UIComponent.None).message}")
                    }
                    else{
                        appendToMessageQueue(dataState.uiComponent)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getLastMeasurement() {
        getLastMeasurement.execute().onEach { dataState ->
            when(dataState){
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
                is DataState.Data -> {
                    if (dataState.data != null) {
                        state.value = state.value.copy(measurementValue = dataState.data.weight)
                    }
                }
                is DataState.Response -> {
                    if(dataState.uiComponent is UIComponent.None){
                        println("getLastMeasurement: ${(dataState.uiComponent as UIComponent.None).message}")
                    }
                    else{
                        appendToMessageQueue(dataState.uiComponent)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteWeightMeasurement(date: Long) {
        deleteMeasurement.execute(date).onEach { dataState ->
            when(dataState){
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
                is DataState.Response -> {
                    if(dataState.uiComponent is UIComponent.None){
                        println("deleteWeightMeasurement: ${(dataState.uiComponent as UIComponent.None).message}")
                    }
                    else {
                        appendToMessageQueue(dataState.uiComponent)
                    }
                    getMeasurements()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sortMeasurements() {
        val sorted = sortMeasurements.execute(
            state.value.weightMeasurements
        )
        state.value = state.value.copy(weightMeasurements = sorted)
    }

    private fun appendToMessageQueue(uiComponent: UIComponent){
        val queue = state.value.messageQueue
        queue.add(uiComponent)
        state.value = state.value.copy(messageQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(messageQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.messageQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(messageQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(messageQueue = queue)
        }catch (e: Exception){
            println("Nothing to remove from DialogQueue")
        }
    }
}