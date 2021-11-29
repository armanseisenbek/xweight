package com.onepercent.xweight.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import com.onepercent.xweight.ui.home.components.line_chart.LineChartFilter
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LinearWeightChart(
    modifier: Modifier = Modifier,
    measurements: List<WeightMeasurement>
) {

    val lineChartFilter = LineChartFilter.TwoWeeks()

    Canvas(
        modifier = modifier,
        onDraw = {

            val canvasHeight = size.height

            val oneDayInMillis = 86400000

            var startDate = 0L

            when(lineChartFilter) {
                is LineChartFilter.TwoWeeks -> {
                    val twoWeeksInMillis = 1209600000

                    val rightNow = System.currentTimeMillis()

                    val remainder = rightNow % oneDayInMillis
                    val correctedDate = rightNow - remainder

                    startDate = correctedDate - twoWeeksInMillis
                }
                else -> {

                }
            }

            val filteredMeasurements = measurements.filter {
                it.date > startDate
            }

            val maxMeasurement = filteredMeasurements
                .maxByOrNull { it.weight }!!.weight.toInt()

            val minMeasurement = filteredMeasurements
                .minByOrNull { it.weight }!!.weight.toInt()

            drawBottomLine(this, startDate)
            drawVerticalLine(
                drawScope = this,
                maxMeasurement = maxMeasurement,
                minMeasurement = minMeasurement,
                canvasHeight = canvasHeight
            )

            // vertical start line
            drawLine(
                start = Offset(x = 0F, y = 0f),
                end = Offset(x = 0F, y = size.height - 40f),
                color = Color.Gray,
                strokeWidth = Stroke.DefaultMiter
            )

            val days = (System.currentTimeMillis() - startDate) / oneDayInMillis

            val totalMeasurements = filteredMeasurements.size

            // Add some kind of a "Padding" for the initial point where the line starts.
            val lineDistance = size.width / days

            filteredMeasurements.forEachIndexed { index, weightMeasurement ->

                if (totalMeasurements >= index + 2) {

                    drawLine(
                        start = Offset(
                            x = (((weightMeasurement.date - startDate) / oneDayInMillis) * lineDistance),
                            y = calculateYCoordinate2(
                                maxMeasurement = maxMeasurement,
                                minMeasurement = minMeasurement,
                                measurementWeight = weightMeasurement.weight,
                                canvasHeight = canvasHeight - 100f
                            )
                        ),
                        end = Offset(
                            x = (((filteredMeasurements[index + 1].date - startDate) / oneDayInMillis) * lineDistance),
                            y = calculateYCoordinate2(
                                maxMeasurement = maxMeasurement,
                                minMeasurement = minMeasurement,
                                measurementWeight = filteredMeasurements[index + 1].weight,
                                canvasHeight = canvasHeight - 100f
                            )
                        ),
                        color = Color(40, 193, 218),
                        strokeWidth = Stroke.DefaultMiter
                    )
                }

                // circle dots
                drawCircle(
                    color = Color.Red,
                    radius = 10f,
                    center = Offset(
                        x = (((weightMeasurement.date - startDate) / oneDayInMillis) * lineDistance),
                        y = calculateYCoordinate2(
                            maxMeasurement = maxMeasurement,
                            minMeasurement = minMeasurement,
                            measurementWeight = weightMeasurement.weight,
                            canvasHeight = canvasHeight - 100f
                        )
                    )
                )

            }

        }
    )
}

private fun calculateYCoordinate(
    maxMeasurementSize: Int,
    measurementWeight: Double,
    canvasHeight: Float
): Float {
    val maxAndCurrentValueDifference = (maxMeasurementSize - measurementWeight).toFloat()
    val relativePercentageOfScreen = (canvasHeight / maxMeasurementSize).toFloat()
    return maxAndCurrentValueDifference * relativePercentageOfScreen
}

private fun calculateYCoordinate2(
    maxMeasurement: Int,
    minMeasurement: Int,
    measurementWeight: Double,
    canvasHeight: Float
): Float {

    val difference = (maxMeasurement+4) - minMeasurement
    val percentage = canvasHeight / difference
    val relativePlace = ((maxMeasurement+4) - measurementWeight) * percentage

    return relativePlace.toFloat()
}

private fun drawBottomLine(
    drawScope: DrawScope,
    startDate: Long
) {
    with(drawScope) {

        // horizontal bottom line
        drawLine(
            start = Offset(x = 0F, y = size.height - 40f),
            end = Offset(x = size.width, y = size.height - 40f),
            color = Color.Gray,
            strokeWidth = Stroke.DefaultMiter
        )

        val oneDayInMillis = 86400000
        val days = (System.currentTimeMillis() - startDate) / oneDayInMillis
        val lineDistance = size.width / days

        for (i in 1..days) {
            drawIntoCanvas {
                val paint = Paint().asFrameworkPaint()
                val labelPaint = paint.apply {
                    textSize = 24f
                    textAlign = android.graphics.Paint.Align.CENTER
                }

                // bottom line labels (dates)
                it.nativeCanvas.drawText(
                    SimpleDateFormat("MM/dd", Locale.getDefault())
                        .format(startDate + (oneDayInMillis * i)).toString(),
                    (i * lineDistance), // x
                    size.height, // y
                    labelPaint
                )

                // bottom line vertical lines
                drawLine(
                    start = Offset(x =(i * lineDistance), y = size.height - 40F),
                    end = Offset(x = (i * lineDistance), y = size.height - 30F),
                    color = Color.Gray,
                    strokeWidth = Stroke.DefaultMiter
                )
            }
        }

    }
}

private fun drawVerticalLine(
    drawScope: DrawScope,
    maxMeasurement: Int,
    minMeasurement: Int,
    canvasHeight: Float
) {
    with(drawScope) {

        val difference = maxMeasurement - minMeasurement
        var measurement = minMeasurement

        for (i in 1..5) {

            drawLine(
                start = Offset(
                    x = 0f,
                    y = calculateYCoordinate2(
                        maxMeasurement = maxMeasurement,
                        minMeasurement = minMeasurement,
                        measurementWeight = measurement.toDouble(),
                        canvasHeight = canvasHeight - 100f
                    ),
                ),
                end = Offset(
                    x = size.width,
                    y = calculateYCoordinate2(
                        maxMeasurement = maxMeasurement,
                        minMeasurement = minMeasurement,
                        measurementWeight = measurement.toDouble(),
                        canvasHeight = canvasHeight - 100f
                    )
                ),
                color = Color.Gray,
                strokeWidth = Stroke.HairlineWidth
            )

            drawIntoCanvas {
                val paint = Paint().asFrameworkPaint()
                val labelPaint = paint.apply {
                    textSize = 24f
                    textAlign = android.graphics.Paint.Align.CENTER
                }

                // vertical line labels
                it.nativeCanvas.drawText(
                    measurement.toString(),
                    0F,
                    calculateYCoordinate2(
                        maxMeasurement = maxMeasurement,
                        minMeasurement = minMeasurement,
                        measurementWeight = measurement.toDouble(),
                        canvasHeight = canvasHeight - 100f
                    ),
                    labelPaint
                )
            }

            measurement += (difference/4)
        }
    }

}