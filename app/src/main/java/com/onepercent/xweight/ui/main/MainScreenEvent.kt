package com.onepercent.xweight.ui.main

import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.ui.main.components.content.line_chart.LineChartFilter
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

sealed class MainScreenEvent {
    object OnRemoveHeadFromQueue: MainScreenEvent()

    object GetAllMeasurements: MainScreenEvent()
    object GetLastMeasurement: MainScreenEvent()

    // Insert Measurement Dialog states
    data class UpdateInsertDialogState(val uiComponentState: UIComponentState): MainScreenEvent()
    data class PickDateForNewMeasurement(val date: Long): MainScreenEvent()
    data class PickValueForNewMeasurement(val weight: Double): MainScreenEvent()
    data class InsertWeightMeasurement(val weightMeasurement: WeightMeasurement): MainScreenEvent()

    data class PickLineChartFilter(val lineChartFilter: LineChartFilter): MainScreenEvent()


}