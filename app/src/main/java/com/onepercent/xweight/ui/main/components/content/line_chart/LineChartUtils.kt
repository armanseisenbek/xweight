package com.onepercent.xweight.ui.main.components.content.line_chart

import com.onepercent.constants.DateConstants.ONE_DAY_IN_MILLIS

object LineChartUtils {

    fun calculateXAxisCoordinate(
        date: Long,
        startDate: Long,
        lineDistance: Float
    ): Float {

        val difference = date - startDate
        val howManyDays = difference / ONE_DAY_IN_MILLIS

        return howManyDays * lineDistance
    }

    fun calculateYAxisCoordinate(
        maxValue: Double,
        minValue: Double,
        currentValue: Double,
        chartHeight: Float
    ): Float {

        val difference: Double = (maxValue) - (minValue)
        val percentage = chartHeight / difference
        val relativePlace = ((maxValue) - currentValue) * percentage

        return relativePlace.toFloat()
    }
}