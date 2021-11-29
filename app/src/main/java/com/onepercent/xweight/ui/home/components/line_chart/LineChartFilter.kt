package com.onepercent.xweight.ui.home.components.line_chart

sealed class LineChartFilter {

    data class TwoWeeks(
        val name: String = "Last 2 weeks"
    ): LineChartFilter()
}