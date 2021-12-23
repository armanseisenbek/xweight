package com.onepercent.xweight.ui.home

import androidx.compose.runtime.mutableStateListOf
import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.domain.Queue
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.ui.home.components.line_chart.LineChartFilter
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

data class HomeScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val lineChartProgressBarState: ProgressBarState = ProgressBarState.Idle,

    val messageQueue: Queue<UIComponent> = Queue(mutableListOf()),

    val weightMeasurements: List<WeightMeasurement> = listOf(),

    val insertDialogState: UIComponentState = UIComponentState.Hide,

    val newMeasurementDate: Long = System.currentTimeMillis(),
    val newMeasurementValue: Double = 15.0,

    val lineChartFilterValue: LineChartFilter = LineChartFilter.TwoWeeks
)