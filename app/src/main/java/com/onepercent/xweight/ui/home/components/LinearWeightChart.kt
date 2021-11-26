package com.onepercent.xweight.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

@Composable
fun LinearWeightChart(
    modifier: Modifier = Modifier,
    measurements: List<WeightMeasurement>
) {
    
    Canvas(
        modifier = modifier,
        onDraw = {

            val totalMeasurements = measurements.size

            val lineDistance = size.width / (totalMeasurements + 1)

            val canvasHeight = size.height

            // Add some kind of a "Padding" for the initial point where the line starts.
            var currentLineDistance = 0F

            measurements.forEachIndexed { index, weightMeasurement ->

                if (totalMeasurements >= index + 2) {

                    drawLine(
                        start = Offset(
                            x = currentLineDistance,
                            y = calculateYCoordinate(
                                maxMeasurementsSize = 200,
                                measurementWeight = weightMeasurement.weight,
                                canvasHeight = canvasHeight
                            )
                        ),
                        end = Offset(
                            x = currentLineDistance + lineDistance,
                            y = calculateYCoordinate(
                                maxMeasurementsSize = 200,
                                measurementWeight = measurements[index + 1].weight,
                                canvasHeight = canvasHeight
                            )
                        ),
                        color = Color(40, 193, 218),
                        strokeWidth = Stroke.DefaultMiter
                    )
                }

                drawCircle(
                    color = Color.Red,
                    radius = 10f,
                    center = Offset(currentLineDistance, calculateYCoordinate(
                        maxMeasurementsSize = 200,
                        measurementWeight = weightMeasurement.weight,
                        canvasHeight = canvasHeight
                    )
                    )
                )

                currentLineDistance += lineDistance
            }
        }
    )
}

private fun calculateYCoordinate(
    maxMeasurementsSize: Int,
    measurementWeight: Double,
    canvasHeight: Float
): Float {
    val maxAndCurrentValueDifference = (maxMeasurementsSize - measurementWeight).toFloat()
    val relativePercentageOfScreen = (canvasHeight / maxMeasurementsSize).toFloat()
    return maxAndCurrentValueDifference * relativePercentageOfScreen
}