package com.onepercent.xweight.ui.home.components

import android.graphics.Typeface
import android.text.StaticLayout
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LinearWeightChart(
    modifier: Modifier = Modifier,
    measurements: List<WeightMeasurement>,
    startDate: Long
) {

    Canvas(
        modifier = modifier,
        onDraw = {

            val canvasHeight = size.height
            val canvasWidth = size.width
            val chartHeight = canvasHeight - 40f
            val chartWidth = canvasWidth - 40f

            val oneDayInMillis = 86400000
            val remainder = System.currentTimeMillis() % oneDayInMillis
            val correctedDate = System.currentTimeMillis() - remainder

            val days = (correctedDate - startDate) / oneDayInMillis

            val maxMeasurement = measurements
                .maxByOrNull { it.weight }!!.weight + 1

            val minMeasurement = measurements
                .minByOrNull { it.weight }!!.weight - 1

            val totalMeasurements = measurements.size

            // Add some kind of a "Padding" for the initial point where the line starts.
            val lineDistance = chartWidth / days

            drawHorizontalLines2(
                drawScope = this,
                chartHeight = chartHeight,
                chartWidth = chartWidth,
                maxMeasurement = maxMeasurement,
                minMeasurement = minMeasurement,
            )

            // Bottom Line Labels
            drawBottomLine(
                drawScope = this,
                canvasWidth = chartWidth,
                chartHeight = chartHeight,
                lineDistance = lineDistance,
                startDate = startDate,
                days = days
            )

//            // vertical start line
//            drawLine(
//                start = Offset(x = 0F, y = 0f),
//                end = Offset(x = 0F, y = canvasHeight),
//                color = Color.Gray,
//                strokeWidth = Stroke.DefaultMiter
//            )

            measurements.forEachIndexed { index, weightMeasurement ->

                if (totalMeasurements >= index + 2) {

                    val xStart = (((weightMeasurement.date - startDate) / oneDayInMillis) * lineDistance)
                    val yStart = calculateYCoordinate2(
                        maxMeasurement = maxMeasurement,
                        minMeasurement = minMeasurement,
                        measurementWeight = weightMeasurement.weight,
                        canvasHeight = chartHeight
                    )

                    val xEnd = (((measurements[index + 1].date - startDate) / oneDayInMillis) * lineDistance)
                    val yEnd = calculateYCoordinate2(
                        maxMeasurement = maxMeasurement,
                        minMeasurement = minMeasurement,
                        measurementWeight = measurements[index + 1].weight,
                        canvasHeight = chartHeight
                    )

                    drawLine(
                        start = Offset(x = xStart, y = yStart),
                        end = Offset(x = xEnd, y = yEnd),
                        color = Color(40, 193, 218),
                        strokeWidth = Stroke.DefaultMiter
                    )

                    val path = Path().also {
                        it.fillType = PathFillType.EvenOdd
                        it.moveTo(xStart, yStart)
                        it.lineTo(xEnd, yEnd)
                        it.lineTo(xEnd, chartHeight)
                        it.lineTo(xStart, chartHeight)
                        it.close()
                    }

                    drawPath(
                        path = path,
                        color = Color(40, 193, 218),
                        alpha = 0.6f
                    )


                }

                // circle dots
                drawCircle(
                    color = Color(40, 193, 218),
                    radius = 7f,
                    center = Offset(
                        x = (((weightMeasurement.date - startDate) / oneDayInMillis) * lineDistance),
                        y = calculateYCoordinate2(
                            maxMeasurement = maxMeasurement,
                            minMeasurement = minMeasurement,
                            measurementWeight = weightMeasurement.weight,
                            canvasHeight = chartHeight
                        )
                    )
                )

//                drawIntoCanvas {
//                    it.nativeCanvas.drawText(
//                        weightMeasurement.weight.toString(),
//                        (((weightMeasurement.date - startDate) / oneDayInMillis) * lineDistance), // x
//                        calculateYCoordinate2(
//                            maxMeasurement = maxMeasurement,
//                            minMeasurement = minMeasurement,
//                            measurementWeight = weightMeasurement.weight,
//                            canvasHeight = chartHeight
//                        ), // y
//                        Paint().asFrameworkPaint().apply {
//                            textSize = 24f
//                            textAlign = android.graphics.Paint.Align.CENTER
//                        }
//                    )
//                }

            }

        }
    )
}

private fun calculateYCoordinate2(
    maxMeasurement: Double,
    minMeasurement: Double,
    measurementWeight: Double,
    canvasHeight: Float
): Float {

    val difference: Double = (maxMeasurement) - (minMeasurement)
    val percentage = canvasHeight / difference
    val relativePlace = ((maxMeasurement) - measurementWeight) * percentage

    return relativePlace.toFloat()
}

private fun drawBottomLine(
    drawScope: DrawScope,
    canvasWidth: Float,
    chartHeight: Float,
    lineDistance: Float,
    startDate: Long,
    days: Long,
) {
    with(drawScope) {

        val oneDayInMillis = 86400000


        val pattern = "MM/dd"
//        val labelLength = Paint().asFrameworkPaint().measureText(pattern, 0, pattern.length)
//        val labelFillableArea = canvasWidth / labelLength
//        Log.d("LinearWeightChart", "drawBottomLine: canvasWidth $canvasWidth")
//        Log.d("LinearWeightChart", "drawBottomLine: labelLength ${labelLength}")
//        Log.d("LinearWeightChart", "drawBottomLine: labelFillableArea $labelFillableArea")

        for (i in 0..days) {
            if (i % 2 == 0L) {
                drawIntoCanvas {
                    // bottom line labels (dates)
                    it.nativeCanvas.drawText(
                        SimpleDateFormat(pattern, Locale.getDefault())
                            .format(startDate + (oneDayInMillis * i)).toString(),
                        (i * lineDistance), // x
                        size.height, // y
                        Paint().asFrameworkPaint().apply {
                            color = 0xFFa2a3a5.toInt()
                            typeface = Typeface.DEFAULT
                            textSize = 24f
                            textAlign = when(i) {
                                0L -> android.graphics.Paint.Align.LEFT
                                days -> android.graphics.Paint.Align.RIGHT
                                else -> android.graphics.Paint.Align.CENTER
                            }
                        }

                    )
                }
            }


        }

    }
}

private fun drawHorizontalLines2(
    drawScope: DrawScope,
    chartHeight: Float,
    chartWidth: Float,
    maxMeasurement: Double,
    minMeasurement: Double
) {
    with(drawScope) {

        val difference: Double = (maxMeasurement) - (minMeasurement)
        var measurement: Double = (minMeasurement)

        val diffValue: Double = difference / 4
        val chartValue = chartHeight / 4

        var chartPlace: Float

        for (i in 0..4) {

            chartPlace = (chartHeight -(chartValue * i))

            drawLine(
                start = Offset(
                    x = 0f,
                    y = chartPlace,
                ),
                end = Offset(
                    x = chartWidth,
                    y = chartPlace
                ),
                color = Color(0xFFeeeeee),
                strokeWidth = 3f
            )

            drawIntoCanvas {
                // vertical line labels
                it.nativeCanvas.drawText(
                    DecimalFormat("#.#")
                        .format(measurement)
                        .replace(",", ".")
                        .toDouble()
                        .toString(),
                    size.width,
                    chartPlace,
                    Paint().asFrameworkPaint().apply {
                        color = 0xFFa2a3a5.toInt()
                        typeface = Typeface.DEFAULT
                        textSize = 25f
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                )
            }

            measurement += diffValue
        }
    }

}