package com.onepercent.xweight.ui.home.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke

import com.onepercent.xweight.ui.home.components.line_chart.LineChartUtils.calculateXAxisCoordinate
import com.onepercent.xweight.ui.home.components.line_chart.LineChartUtils.calculateYAxisCoordinate
import com.onepercent.xweight.ui.home.components.line_chart.XAxisLabelDrawer
import com.onepercent.xweight.ui.home.components.line_chart.YAxisLabelDrawer
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

@Composable
fun LinearWeightChart(
    modifier: Modifier = Modifier,
    measurements: List<WeightMeasurement>,
    maxMeasurement: Double,
    minMeasurement: Double,
    startDate: Long,
    days: Int,
    chartColor: Color = Color(40, 193, 218),
    xAxisLabelDrawer: XAxisLabelDrawer = XAxisLabelDrawer(),
    yAxisLabelDrawer: YAxisLabelDrawer = YAxisLabelDrawer()
) {

    Canvas(
        modifier = modifier,
        onDraw = {

            val canvasHeight = size.height
            val canvasWidth = size.width

            // chartHeight, chartWidth, lineDistance simple calculations
            val chartHeight = canvasHeight - ((canvasHeight / 100) * 7.25f)
            val chartWidth = canvasWidth - ((canvasWidth / 100) * 4f)
            val lineDistance = chartWidth / (days - 1)

            // yAxis labels
            yAxisLabelDrawer.drawAxisLabels(
                drawScope = this,
                chartHeight = chartHeight,
                chartWidth = chartWidth,
                maxMeasurement = maxMeasurement,
                minMeasurement = minMeasurement,
            )

            // xAxis labels
            xAxisLabelDrawer.drawAxisLabels(
                drawScope = this,
                chartWidth = chartWidth,
                chartHeight = chartHeight,
                lineDistance = lineDistance,
                startDate = startDate,
                days = days.toLong()
            )

            measurements.forEachIndexed { index, weightMeasurement ->

                val xStart = calculateXAxisCoordinate(
                    date = weightMeasurement.date,
                    startDate = startDate,
                    lineDistance = lineDistance
                )

                val yStart = calculateYAxisCoordinate(
                    maxValue = maxMeasurement,
                    minValue = minMeasurement,
                    currentValue = weightMeasurement.weight,
                    chartHeight = chartHeight
                )

                // circle dots
                drawCircle(
                    color = chartColor,
                    radius = 7f,
                    center = Offset(x = xStart, y = yStart)
                )

                // draw lines and fill areas
                if (measurements.size >= index + 2) {

                    val xEnd =  calculateXAxisCoordinate(
                        date = measurements[index + 1].date,
                        startDate = startDate,
                        lineDistance = lineDistance
                    )

                    val yEnd = calculateYAxisCoordinate(
                        maxValue = maxMeasurement,
                        minValue = minMeasurement,
                        currentValue = measurements[index + 1].weight,
                        chartHeight = chartHeight
                    )

                    // chart lines
                    drawLine(
                        start = Offset(x = xStart, y = yStart),
                        end = Offset(x = xEnd, y = yEnd),
                        color = chartColor,
                        strokeWidth = Stroke.DefaultMiter
                    )

                    // chart filled areas
                    drawPath(
                        path = Path().apply {
                            fillType = PathFillType.EvenOdd
                            moveTo(xStart, yStart)
                            lineTo(xEnd, yEnd)
                            lineTo(xEnd, chartHeight)
                            lineTo(xStart, chartHeight)
                            close()
                        },
                        color = chartColor,
                        alpha = 0.6f
                    )

                }

            }

        }
    )
}