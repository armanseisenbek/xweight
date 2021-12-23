package com.onepercent.xweight.ui.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.ui.home.components.line_chart.LineChartFilter
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

@Composable
fun LineChartDashboard(
    progressBarState: ProgressBarState,
    weightMeasurements: List<WeightMeasurement>
) {

    Column {

        Row(modifier = Modifier.fillMaxWidth()) {

        }


        if (progressBarState != ProgressBarState.Loading) {

            val lineChartFilter = LineChartFilter.TwoWeeks()
            val oneDayInMillis = 86400000
            var startDate = 0L

            val remainder = System.currentTimeMillis() % oneDayInMillis
            val correctedDate = System.currentTimeMillis() - remainder

            when(lineChartFilter) {
                is LineChartFilter.TwoWeeks -> {
                    val twoWeeksInMillis = oneDayInMillis * 14
                    startDate = (correctedDate - twoWeeksInMillis) + oneDayInMillis
                }
                else -> {}
            }

            val filteredMeasurements = weightMeasurements.filter {
                it.date >= startDate
            }

            val maxMeasurement = filteredMeasurements
                .maxByOrNull { it.weight }!!.weight

            val minMeasurement = filteredMeasurements
                .minByOrNull { it.weight }!!.weight

            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(text = "Measurements size = ${filteredMeasurements.size}")
                    Text(text = "Max measurement = $maxMeasurement")
                    Text(text = "Min measurement = $minMeasurement")
                }
            }

            LinearWeightChart(
                modifier = Modifier
                    .fillMaxWidth()
                    //.border(1.dp, Color.Blue)
                    .padding(15.dp)
                    .fillMaxHeight(fraction = 0.3f),
                measurements = filteredMeasurements,
                startDate = startDate
            )
        }

    }
}