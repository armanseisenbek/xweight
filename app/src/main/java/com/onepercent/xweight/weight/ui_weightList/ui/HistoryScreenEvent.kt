package com.onepercent.xweight.weight.ui_weightList.ui

import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

sealed class HistoryScreenEvent {

    object GetAllMeasurements: HistoryScreenEvent()
    object GetLastMeasurement: HistoryScreenEvent()

    object OnRemoveHeadFromQueue: HistoryScreenEvent()

    data class InsertWeightMeasurement(val weightMeasurement: WeightMeasurement): HistoryScreenEvent()

    data class DeleteWeightMeasurement(val date: Long): HistoryScreenEvent()

    data class UpdateFabDialogState(val uiComponentState: UIComponentState): HistoryScreenEvent()

    data class UpdateEditMeasurementDialogState(val uiComponentState: UIComponentState): HistoryScreenEvent()

    data class PickDateForNewMeasurement(val date: Long): HistoryScreenEvent()

    data class PickValueForNewMeasurement(val weight: Double): HistoryScreenEvent()

    data class PickMeasurementForEdit(val date: Long, val weight: Double): HistoryScreenEvent()
}