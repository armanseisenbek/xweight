package com.onepercent.xweight.ui.main.components.content.line_chart

import android.graphics.Typeface

import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas

import com.onepercent.xweight.core.util.Constants.Companion.ONE_DAY_IN_MILLIS

import java.text.SimpleDateFormat
import java.util.*

class XAxisLabelDrawer {

    fun drawAxisLabels(
        drawScope: DrawScope,
        chartWidth: Float,
        chartHeight: Float,
        lineDistance: Float,
        startDate: Long,
        days: Long,
        pattern: String = "MM/dd"
    ) {
        with(drawScope) {

            for (i in 0 until days) {
                if (i % (days / 7) == 0L) {
                    drawIntoCanvas {
                        val labelText = SimpleDateFormat(pattern, Locale.getDefault())
                            .format(startDate + (ONE_DAY_IN_MILLIS * i)).toString()

                        it.nativeCanvas.drawText(
                            labelText,
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
}

//        val labelLength = Paint().asFrameworkPaint().measureText(pattern, 0, pattern.length)
//        val labelFillableArea = canvasWidth / labelLength
//        Log.d("LinearWeightChart", "drawBottomLine: canvasWidth $canvasWidth")
//        Log.d("LinearWeightChart", "drawBottomLine: labelLength ${labelLength}")
//        Log.d("LinearWeightChart", "drawBottomLine: labelFillableArea $labelFillableArea")
