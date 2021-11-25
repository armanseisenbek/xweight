package com.onepercent.xweight.weight.ui_weightList.ui

import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.domain.Queue
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

data class HistoryScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val messageQueue: Queue<UIComponent> = Queue(mutableListOf()),

    val weightMeasurements: List<WeightMeasurement> = listOf(),
    val measurementDate: Long = System.currentTimeMillis(),
    val measurementValue: Double = 0.0,

    val fabDialogState: UIComponentState = UIComponentState.Hide, // show/hide the fab dialog
    val editMeasurementDialogState: UIComponentState = UIComponentState.Hide, // show/hide the edit dialog
)