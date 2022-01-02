package com.onepercent.xweight.ui.main.components.content.line_chart

import android.graphics.Typeface

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas

import com.onepercent.xweight.core.util.formatWeight

class YAxisLabelDrawer {

    fun drawAxisLabels(
        drawScope: DrawScope,
        chartHeight: Float,
        chartWidth: Float,
        maxMeasurement: Double,
        minMeasurement: Double
    ) {
        with(drawScope) {

            val difference: Double = (maxMeasurement) - (minMeasurement)
            var measurementValue: Double = (minMeasurement)

            val measurementValuePortion: Double = difference / 4
            val yAxisPortion = chartHeight / 4

            var yAxisPoint: Float

            // there is 5 yaxis lines with 5 labels
            for (i in 0..4) {

                yAxisPoint = (chartHeight - (yAxisPortion * i))

                // yAxis lines
                drawLine(
                    start = Offset(x = 0f, y = yAxisPoint),
                    end = Offset(x = chartWidth, y = yAxisPoint),
                    color = Color(0xFFeeeeee),
                    strokeWidth = 3f
                )

                // yAxis labels
                drawIntoCanvas {

                    val labelText = measurementValue.formatWeight("#.#")

                    val labelPaint = Paint().asFrameworkPaint().apply {
                        color = 0xFFa2a3a5.toInt()
                        typeface = Typeface.DEFAULT
                        textSize = 25f
                        textAlign = android.graphics.Paint.Align.CENTER
                    }

                    it.nativeCanvas.drawText(
                        labelText,
                        size.width,
                        yAxisPoint,
                        labelPaint
                    )
                }

                measurementValue += measurementValuePortion
            }
        }

    }
}