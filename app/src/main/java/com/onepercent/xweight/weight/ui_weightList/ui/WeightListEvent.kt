package com.onepercent.xweight.weight.ui_weightList.ui

import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

sealed class WeightListEvent {

    object GetAllMeasurements: WeightListEvent()
    object GetLastMeasurement: WeightListEvent()

    object OnRemoveHeadFromQueue: WeightListEvent()

    data class InsertWeightMeasurement(val weightMeasurement: WeightMeasurement): WeightListEvent()

    data class DeleteWeightMeasurement(val date: Long): WeightListEvent()

    data class UpdateFabDialogState(val uiComponentState: UIComponentState): WeightListEvent()

    data class UpdateEditMeasurementDialogState(val uiComponentState: UIComponentState): WeightListEvent()

    data class PickDateForNewMeasurement(val date: Long): WeightListEvent()

    data class PickValueForNewMeasurement(val weight: Double): WeightListEvent()

    data class PickMeasurementForEdit(val date: Long, val weight: Double): WeightListEvent()
}