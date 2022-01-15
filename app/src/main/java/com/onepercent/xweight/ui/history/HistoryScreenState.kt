package com.onepercent.xweight.ui.history

import com.onepercent.weight_domain.WeightMeasurement
import com.onepercent.core.domain.ProgressBarState
import com.onepercent.core.domain.Queue
import com.onepercent.core.domain.UIComponent
import com.onepercent.core.domain.UIComponentState

data class HistoryScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val messageQueue: Queue<UIComponent> = Queue(mutableListOf()),

    val weightMeasurements: List<WeightMeasurement> = listOf(),
    val measurementDate: Long = System.currentTimeMillis(),
    val measurementValue: Double = 0.0,

    val editMeasurementDialogState: UIComponentState = UIComponentState.Hide, // show/hide the edit dialog
)