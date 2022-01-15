package com.onepercent.xweight.ui.main

import com.onepercent.weight_domain.WeightMeasurement
import com.onepercent.core.domain.ProgressBarState
import com.onepercent.core.domain.Queue
import com.onepercent.core.domain.UIComponent
import com.onepercent.core.domain.UIComponentState
import com.onepercent.xweight.ui.main.components.content.line_chart.LineChartFilter

data class MainScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val lineChartProgressBarState: ProgressBarState = ProgressBarState.Idle,

    val messageQueue: Queue<UIComponent> = Queue(mutableListOf()),

    val weightMeasurements: List<WeightMeasurement> = listOf(),

    val insertDialogState: UIComponentState = UIComponentState.Hide,

    val newMeasurementDate: Long = System.currentTimeMillis(),
    val newMeasurementValue: Double = 15.0,

    val lineChartFilterValue: LineChartFilter = LineChartFilter.TwoWeeks
)