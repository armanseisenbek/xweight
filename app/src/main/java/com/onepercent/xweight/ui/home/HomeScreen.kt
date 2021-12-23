package com.onepercent.xweight.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.ui.components.DefaultScreenUI
import com.onepercent.xweight.ui.home.components.InsertMeasurementDialog
import com.onepercent.xweight.ui.home.components.LineChartDashboard

@Composable
fun HomeScreen(
    state: HomeScreenState,
    events: (HomeScreenEvent) -> Unit,
) {

    DefaultScreenUI(
        progressBarState = state.progressBarState,
        queue = state.messageQueue,
        onRemoveHeadFromQueue = {
            events(HomeScreenEvent.OnRemoveHeadFromQueue)
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                //.border(10.dp, Color.Black)
                .padding(10.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        //.border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {

                    LineChartDashboard(
                        progressBarState = state.progressBarState,
                        weightMeasurements = state.weightMeasurements
                    )

                }
            }
        }

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.7f)
                ,
                shape = RoundedCornerShape(40.dp),
                onClick = {
                    events(HomeScreenEvent.GetLastMeasurement)
                    events(HomeScreenEvent.UpdateInsertDialogState(UIComponentState.Show))
                },
            ) {
                Text(modifier = Modifier.padding(vertical = 6.dp), text = "NEW WEIGHT")
            }
        }

        if (state.insertDialogState is UIComponentState.Show) {
            if (state.progressBarState != ProgressBarState.Loading) {
                InsertMeasurementDialog(
                    onInsertWeightMeasurement = {
                        events(HomeScreenEvent.InsertWeightMeasurement(it))
                    },
                    onPickDate = {
                        events(HomeScreenEvent.PickDateForNewMeasurement(it))
                    },
                    onPickValue = {
                        events(HomeScreenEvent.PickValueForNewMeasurement(it))
                    },
                    onCloseDialog = {
                        events(HomeScreenEvent.UpdateInsertDialogState(UIComponentState.Hide))
                    },
                    measurementDate = state.newMeasurementDate,
                    measurementValue = state.newMeasurementValue
                )
            }
        }
    }
}