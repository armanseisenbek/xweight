package com.onepercent.xweight.ui.home

import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

sealed class HomeScreenEvent {
    object OnRemoveHeadFromQueue: HomeScreenEvent()

    object GetAllMeasurements: HomeScreenEvent()
    object GetLastMeasurement: HomeScreenEvent()

    // Insert Measurement Dialog states
    data class UpdateInsertDialogState(val uiComponentState: UIComponentState): HomeScreenEvent()
    data class PickDateForNewMeasurement(val date: Long): HomeScreenEvent()
    data class PickValueForNewMeasurement(val weight: Double): HomeScreenEvent()
    data class InsertWeightMeasurement(val weightMeasurement: WeightMeasurement): HomeScreenEvent()


}