package com.onepercent.xweight.ui.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.core.util.Constants.Companion.ONE_DAY_IN_MILLIS
import com.onepercent.xweight.ui.components.DefaultScreenUI
import com.onepercent.xweight.ui.home.components.InsertMeasurementDialog
import com.onepercent.xweight.ui.home.components.LineChartDashboard
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

@Composable
fun HomeScreen(
    state: HomeScreenState,
    events: (HomeScreenEvent) -> Unit,
) {

    Log.d("HomeScreen", "HomeScreen: recompose")

    DefaultScreenUI(
        progressBarState = state.progressBarState,
        queue = state.messageQueue,
        onRemoveHeadFromQueue = {
            events(HomeScreenEvent.OnRemoveHeadFromQueue)
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Column(modifier = Modifier.fillParentMaxHeight()) {

                    LineChartDashboard(
                        progressBarState = state.lineChartProgressBarState,
                        weightMeasurements = state.weightMeasurements,
                        lineChartFilterValue = state.lineChartFilterValue,
                        pickLineChartFilter = {
                            events(HomeScreenEvent.PickLineChartFilter(it))
                        },
                    )
                }
            }
        }

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.7f),
                shape = RoundedCornerShape(40.dp),
                onClick = {
                    events(HomeScreenEvent.GetLastMeasurement)
                    events(HomeScreenEvent.UpdateInsertDialogState(UIComponentState.Show))
                },
                content = {
                    Text(modifier = Modifier.padding(vertical = 6.dp), text = "NEW WEIGHT")
                }
            )
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