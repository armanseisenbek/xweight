package com.onepercent.xweight.ui.home

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.ui.components.GenericDialog

@Composable
fun HomeScreen(
    state: HomeScreenState,
    events: (HomeScreenEvent) -> Unit,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

        },
        content = {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {

                LinearWeightChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .border(1.dp, Color.Black)
                        .padding(10.dp)
                        .fillMaxHeight(fraction = 0.4f),
                    measurements = state.weightMeasurements
                )

                //            FloatingActionButton(
//                onClick = {
//                    events(HistoryScreenEvent.GetLastMeasurement)
//                    events(HistoryScreenEvent.UpdateFabDialogState(UIComponentState.Show))
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Add,
//                    contentDescription = "add new weight measurement"
//                )
//            }
//                if (state.fabDialogState is UIComponentState.Show) {
//                    InsertMeasurementDialog(
//                        onInsertWeightMeasurement = { events(HistoryScreenEvent.InsertWeightMeasurement(it)) },
//                        onPickDate = { events(HistoryScreenEvent.PickDateForNewMeasurement(it)) },
//                        onPickValue = { events(HistoryScreenEvent.PickValueForNewMeasurement(it)) },
//                        onCloseDialog = { events(
//                            HistoryScreenEvent.UpdateFabDialogState(
//                                UIComponentState.Hide)) },
//                        measurementDate = state.measurementDate,
//                        measurementValue = state.measurementValue
//                    )
//                }


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
                                    events(HomeScreenEvent.OnRemoveHeadFromQueue)
                                }
                            )
                        }
                        else if (uiComponent is UIComponent.Toast) {
                            Toast.makeText(LocalContext.current, uiComponent.message, Toast.LENGTH_SHORT).show()
                            events(HomeScreenEvent.OnRemoveHeadFromQueue)
                        }

                    }
                }
            }
        },
    )
}