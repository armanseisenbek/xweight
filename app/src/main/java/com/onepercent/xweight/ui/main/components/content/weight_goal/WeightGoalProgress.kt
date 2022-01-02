package com.onepercent.xweight.ui.main.components.content.weight_goal

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp
import java.lang.Math.round

@Composable
fun WeightGoalProgress() {

    val sliderValue by remember { mutableStateOf(0.5f) }

    Canvas(
        modifier = Modifier
            .width(275.dp)
            .height(275.dp),
            //.padding(16.dp),
        onDraw = {


            drawArc(
                brush = SolidColor(Color.LightGray),
                startAngle = 150f,
                sweepAngle = 240f,
                useCenter = false,
                style = Stroke(35f, cap = StrokeCap.Round)
            )

            val convertedValue = sliderValue * 240

            drawArc(
                brush = SolidColor(Color(40, 193, 218)),
                startAngle = 150f,
                sweepAngle = convertedValue,
                useCenter = false,
                style = Stroke(35f, cap = StrokeCap.Round)
            )

            drawIntoCanvas {
                val paint = Paint().asFrameworkPaint()
                paint.apply {
                    isAntiAlias = true
                    textSize = 55f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
                it.nativeCanvas.drawText(
                    "${round(sliderValue * 100).toInt()}%",
                    size.width / 2,
                    size.height / 2,
                    paint
                )
            }



        }
    )

}