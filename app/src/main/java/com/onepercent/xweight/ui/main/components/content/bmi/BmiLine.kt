package com.onepercent.xweight.ui.main.components.content.bmi

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun BmiLine(
    modifier: Modifier
) {
    Canvas(
        modifier = modifier,
        onDraw = {

            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = calculateLength(size.width, 18.5), y = 0f),
                color = Color.Blue,
                strokeWidth = 15f
            )

            drawLine(
                start = Offset(x = calculateLength(size.width, 18.5), y = 0f),
                end = Offset(x = calculateLength(size.width, 25.0), y = 0f),
                color = Color.Green,
                strokeWidth = 15f
            )

            drawLine(
                start = Offset(x = calculateLength(size.width, 25.0), y = 0f),
                end = Offset(x = calculateLength(size.width, 30.0), y = 0f),
                color = Color.Yellow,
                strokeWidth = 15f
            )

            drawLine(
                start = Offset(x = calculateLength(size.width, 30.0), y = 0f),
                end = Offset(x = calculateLength(size.width, 40.0), y = 0f),
                color = Color.Red,
                strokeWidth = 15f
            )



            drawCircle(
                color = Color.Black,
                radius = 15f,
                center = Offset(x = calculateLength(size.width, 24.4), y = 0f)
            )

            drawCircle(
                color = Color.Green,
                radius = 12f,
                center = Offset(x = calculateLength(size.width, 24.4), y = 0f)
            )
        }
    )
}

fun calculateLength(
    lineLength: Float,
    currentVal: Double
): Float {
    val diff = 24
    val perc = lineLength / diff
    val place = (40 - currentVal) * perc
    val realPlace = lineLength - place

    return realPlace.toFloat()
}