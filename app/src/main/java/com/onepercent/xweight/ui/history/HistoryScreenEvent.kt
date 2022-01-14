package com.onepercent.xweight.ui.history

import com.onepercent.weight_domain.WeightMeasurement
import com.onepercent.xweight.core.domain.UIComponentState

sealed class HistoryScreenEvent {

    object GetAllMeasurements: HistoryScreenEvent()
    object OnRemoveHeadFromQueue: HistoryScreenEvent()

    data class InsertWeightMeasurement(val weightMeasurement: WeightMeasurement): HistoryScreenEvent()

    data class DeleteWeightMeasurement(val date: Long): HistoryScreenEvent()

    data class UpdateEditMeasurementDialogState(val uiComponentState: UIComponentState): HistoryScreenEvent()

    data class PickValueForNewMeasurement(val weight: Double): HistoryScreenEvent()

    data class PickMeasurementForEdit(val date: Long, val weight: Double): HistoryScreenEvent()
}