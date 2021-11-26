package com.onepercent.xweight.ui.home

import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.domain.Queue
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

data class HomeScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val messageQueue: Queue<UIComponent> = Queue(mutableListOf()),

    val weightMeasurements: List<WeightMeasurement> = listOf(),

    val insertDialogState: UIComponentState = UIComponentState.Hide,

    val newMeasurementDate: Long = System.currentTimeMillis(),
    val newMeasurementValue: Double = 0.0,
)