package com.onepercent.xweight.ui.home.components.line_chart

import com.onepercent.xweight.core.util.Constants

object LineChartUtils {

    fun calculateXAxisCoordinate(
        date: Long,
        startDate: Long,
        lineDistance: Float
    ): Float {

        val difference = date - startDate
        val howManyDays = difference / Constants.ONE_DAY_IN_MILLIS

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