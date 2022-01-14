package com.onepercent.xweight.ui.history

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.onepercent.weight_domain.WeightMeasurement

import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.ui.components.DefaultScreenUI
import com.onepercent.xweight.ui.theme.XweightTheme
import com.onepercent.xweight.ui.history.components.EditMeasurementDialog
import com.onepercent.xweight.ui.history.components.HistoryScreenTopBar
import com.onepercent.xweight.ui.history.components.MeasurementHistoryList
import com.onepercent.xweight.ui.history.HistoryScreenEvent.*

@Composable
fun HistoryScreen(
    state: HistoryScreenState,
    events: (HistoryScreenEvent) -> Unit,
) {
    DefaultScreenUI(
        queue = state.messageQueue,
        onRemoveHeadFromQueue = { events(OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
    ) {
        Column {

            HistoryScreenTopBar()

            MeasurementHistoryList(
                modifier = Modifier.fillMaxSize(),
                weightMeasurements = state.weightMeasurements,
                onSelectMeasurement = { date, weight ->
                    events(PickMeasurementForEdit(date, weight))
                    events(UpdateEditMeasurementDialogState(UIComponentState.Show))
                }
            )

            if (state.editMeasurementDialogState is UIComponentState.Show) {
                EditMeasurementDialog(
                    onInsertWeightMeasurement = { weightMeasurement ->
                        events(InsertWeightMeasurement(weightMeasurement))
                    },
                    onDeleteMeasurement = { date ->
                        events(DeleteWeightMeasurement(date))
                    },
                    onPickValue = { value ->
                        events(PickValueForNewMeasurement(value))
                    },
                    onCloseDialog = {
                        events(UpdateEditMeasurementDialogState(UIComponentState.Hide))
                    },
                    measurementValue = state.measurementValue,
                    measurementDate = state.measurementDate
                )
            }
        }
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    XweightTheme {
        HistoryScreen(
            state = HistoryScreenState(
                weightMeasurements = listOf(
                    WeightMeasurement(1, 67.6, 0),
                    WeightMeasurement(1, 66.6, 0),
                    WeightMeasurement(1, 66.8, 0),
                    WeightMeasurement(1, 65.6, 0),
                )
            ),
            events = {}
        )
    }
}