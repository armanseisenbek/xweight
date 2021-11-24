package com.onepercent.xweight.weight.ui_weightList.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.ui.components.CircularIndeterminateProgressBar
import com.onepercent.xweight.ui.components.GenericDialog
import com.onepercent.xweight.weight.ui_weightList.components.EditMeasurementDialog
import com.onepercent.xweight.weight.ui_weightList.components.insert_dialog.InsertMeasurementDialog
import com.onepercent.xweight.weight.ui_weightList.components.WeightListItem
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

@Composable
fun WeightList(
    state: WeightListState,
    events: (WeightListEvent) -> Unit,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "History") },
                actions = {
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(Icons.Filled.Sort, contentDescription = "Sorting by date")
//                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    events(WeightListEvent.GetLastMeasurement)
                    events(WeightListEvent.UpdateFabDialogState(UIComponentState.Show))
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add new weight measurement"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ){

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(state.weightMeasurements) { index: Int, item: WeightMeasurement ->
                    WeightListItem(
                        weightMeasurement = item,
                        previousMeasurement = if (index + 1 < state.weightMeasurements.size) {
                                state.weightMeasurements[index+1]
                            } else {
                                null
                            }
                        ,
                        onSelectMeasurement = { date, weight ->
                            events(WeightListEvent.PickMeasurementForEdit(date, weight))
                            events(WeightListEvent.UpdateEditMeasurementDialogState(UIComponentState.Show))
                        }
                    )
                }
            }

            // process the queue
            if(!state.messageQueue.isEmpty()){
                state.messageQueue.peek()?.let { uiComponent ->
                    if(uiComponent is UIComponent.Dialog){
                        GenericDialog(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                            ,
                            title = uiComponent.title,
                            description = uiComponent.description,
                            onRemoveHeadFromQueue = {
                                events(WeightListEvent.OnRemoveHeadFromQueue)
                            }
                        )
                    }
                    else if (uiComponent is UIComponent.Toast) {
                        Toast.makeText(LocalContext.current, uiComponent.message, Toast.LENGTH_SHORT).show()
                        events(WeightListEvent.OnRemoveHeadFromQueue)
                    }

                }
            }

            if(state.progressBarState is ProgressBarState.Loading){
                CircularIndeterminateProgressBar()
            }

            if (state.fabDialogState is UIComponentState.Show) {
                InsertMeasurementDialog(
                    onInsertWeightMeasurement = { events(WeightListEvent.InsertWeightMeasurement(it)) },
                    onPickDate = { events(WeightListEvent.PickDateForNewMeasurement(it)) },
                    onPickValue = { events(WeightListEvent.PickValueForNewMeasurement(it)) },
                    onCloseDialog = { events(WeightListEvent.UpdateFabDialogState(UIComponentState.Hide)) },
                    measurementDate = state.measurementDate,
                    measurementValue = state.measurementValue
                )
            }

            if (state.editMeasurementDialogState is UIComponentState.Show) {
                EditMeasurementDialog(
                    onInsertWeightMeasurement = { events(WeightListEvent.InsertWeightMeasurement(it)) },
                    onDeleteMeasurement = { events(WeightListEvent.DeleteWeightMeasurement(it)) },
                    onCloseDialog = { events(WeightListEvent.UpdateEditMeasurementDialogState(UIComponentState.Hide)) },
                    onPickValue = { events(WeightListEvent.PickValueForNewMeasurement(it)) },
                    measurementValue = state.measurementValue,
                    measurementDate = state.measurementDate
                )
            }
        }
    }
}