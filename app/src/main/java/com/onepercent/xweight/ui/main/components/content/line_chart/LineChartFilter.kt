package com.onepercent.xweight.ui.main.components.content.line_chart

import androidx.annotation.StringRes
import com.onepercent.xweight.R

sealed class LineChartFilter(
    @StringRes val label: Int,
    val millis: Long,
    val days: Int
) {

    object TwoWeeks : LineChartFilter(
        label = R.string.last_2_weeks,
        millis = 1209600000,
        days = 14
    )

    object Month: LineChartFilter(
        label = R.string.last_30_days,
        millis = 2592000000,
        days = 30
    )

    object NinetyDays: LineChartFilter(
        label = R.string.last_90_days,
        millis = 7776000000,
        days = 90
    )

    object Year: LineChartFilter(
        label = R.string.year,
        millis = 31536000000,
        days = 365
    )
}