package com.onepercent.xweight.ui.main.components.content

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.onepercent.weight_domain.WeightMeasurement

import com.onepercent.core.domain.ProgressBarState
import com.onepercent.core.domain.UIComponentState
import com.onepercent.xweight.ui.components.DefaultScreenUI
import com.onepercent.xweight.ui.main.MainScreenEvent
import com.onepercent.xweight.ui.main.MainScreenEvent.*
import com.onepercent.xweight.ui.main.MainScreenState
import com.onepercent.xweight.ui.main.components.fab.InsertMeasurementDialog
import com.onepercent.xweight.ui.main.components.content.line_chart.LineChartDashboard
import com.onepercent.xweight.ui.main.components.content.weight_goal.WeightGoal

@Composable
fun MainScreenContent(
    state: MainScreenState,
    events: (MainScreenEvent) -> Unit,
) {

    Log.d("MainScreenContent", "recompose")

    DefaultScreenUI(
        progressBarState = state.progressBarState,
        queue = state.messageQueue,
        onRemoveHeadFromQueue = {
            events(OnRemoveHeadFromQueue)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            WeightGoal(
                startWeight = WeightMeasurement(weight = 59.9, date = 1610117600000),
                currentWeight = state.newMeasurementValue,
//                currentWeight = 65.5,
                goalWeight = 75.0
            )

            LineChartDashboard(
                progressBarState = state.lineChartProgressBarState,
                weightMeasurements = state.weightMeasurements,
                lineChartFilterValue = state.lineChartFilterValue,
                pickLineChartFilter = {
                    events(PickLineChartFilter(it))
                }
            )


            Spacer(Modifier.height(50.dp))
        }

        if (state.insertDialogState is UIComponentState.Show) {
            if (state.progressBarState != ProgressBarState.Loading) {
                InsertMeasurementDialog(
                    onInsertWeightMeasurement = {
                        events(InsertWeightMeasurement(it))
                    },
                    onPickDate = {
                        events(PickDateForNewMeasurement(it))
                    },
                    onPickValue = {
                        events(PickValueForNewMeasurement(it))
                    },
                    onCloseDialog = {
                        events(UpdateInsertDialogState(UIComponentState.Hide))
                    },
                    measurementDate = state.newMeasurementDate,
                    measurementValue = state.newMeasurementValue
                )
            }
        }
    }
}