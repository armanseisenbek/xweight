package com.onepercent.xweight.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onepercent.xweight.core.domain.DataState
import com.onepercent.xweight.core.domain.Queue
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement
import com.onepercent.xweight.weight.weight_interactors.GetAllMeasurements
import com.onepercent.xweight.weight.weight_interactors.GetLastMeasurement
import com.onepercent.xweight.weight.weight_interactors.InsertWeightMeasurement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    private val getAllMeasurements: GetAllMeasurements,
    private val getLastMeasurement: GetLastMeasurement,
    private val insertWeightMeasurement: InsertWeightMeasurement,
) : ViewModel() {

    val state: MutableState<HomeScreenState> = mutableStateOf(HomeScreenState())

    init {
        onTriggerEvent(HomeScreenEvent.GetAllMeasurements)
    }

    fun onTriggerEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            is HomeScreenEvent.GetAllMeasurements -> {
                getMeasurements()
            }
            is HomeScreenEvent.GetLastMeasurement -> {
                getLastMeasurement()
            }
            is HomeScreenEvent.UpdateInsertDialogState -> {
                state.value = state.value.copy(
                    insertDialogState = event.uiComponentState,
                    newMeasurementDate = System.currentTimeMillis()
                )
            }
            is HomeScreenEvent.PickDateForNewMeasurement -> {
                state.value = state.value.copy(newMeasurementDate = event.date)
            }
            is HomeScreenEvent.PickValueForNewMeasurement -> {
                state.value = state.value.copy(newMeasurementValue = event.weight)
            }
            is HomeScreenEvent.InsertWeightMeasurement -> {
                insertWeightMeasurement(event.weightMeasurement)
            }
            is HomeScreenEvent.PickLineChartFilter -> {
                state.value = state.value.copy(
                    lineChartFilterValue = event.lineChartFilter
                )
            }
        }
    }

    private fun getMeasurements() {
        getAllMeasurements.execute().onEach { dataState ->
            when(dataState){
                is DataState.Loading -> {
                    state.value = state.value.copy(lineChartProgressBarState = dataState.progressBarState)
                }
                is DataState.Data -> {
                    // sorted by ascending
                    val list = (dataState.data ?: listOf())
                        .sortedBy { it.date }.toMutableList()

                    state.value = state.value.copy(weightMeasurements = list)
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
                        state.value = state.value.copy(newMeasurementValue = dataState.data.weight)
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