package com.onepercent.xweight.ui.main.components.content.line_chart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.onepercent.weight_domain.WeightMeasurement

import com.onepercent.core.domain.ProgressBarState
import com.onepercent.xweight.Constants.Companion.ONE_DAY_IN_MILLIS
import com.onepercent.core.util.formatWeight

@Composable
fun LineChartDashboard(
    progressBarState: ProgressBarState,
    weightMeasurements: List<WeightMeasurement>,
    lineChartFilterValue: LineChartFilter,
    pickLineChartFilter: (LineChartFilter) -> Unit,
) {

    val todayS = System.currentTimeMillis()
    val remainder = todayS % ONE_DAY_IN_MILLIS
    val correctedDate = todayS - remainder
    val startDate = (correctedDate - lineChartFilterValue.millis) + ONE_DAY_IN_MILLIS

    val filteredMeasurements = weightMeasurements.filter { it.date >= startDate }

    var maxMeasurement = 0.0
    var minMeasurement = 0.0
    var rowText = "No data for the ${stringResource(lineChartFilterValue.label)}"

    if (filteredMeasurements.isNotEmpty()) {

        maxMeasurement = filteredMeasurements.maxByOrNull { it.weight }!!.weight + 1
        minMeasurement = filteredMeasurements.minByOrNull { it.weight }!!.weight - 1

        val average = filteredMeasurements.map { it.weight }.average()

        rowText = "average weight - ${average.formatWeight()} kg"
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .border(1.dp, Color(0xFFeeeeee), RoundedCornerShape(5.dp))
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = rowText,
                    color = Color(0xFF494d50)
                )
                Column {
                    LineChartFilterPicker(
                        lineChartFilterValue = lineChartFilterValue,
                        pickLineChartFilterValue = pickLineChartFilter
                    )
                }
            }
        )

//        if (progressBarState != ProgressBarState.Loading) {

            LineChart(
                modifier = Modifier
                    .height(250.dp)
                    .padding(5.dp)
                    .fillMaxWidth()
                    .padding(5.dp, 15.dp, 15.dp, 15.dp),
                measurements = filteredMeasurements,
                maxMeasurement = maxMeasurement,
                minMeasurement = minMeasurement,
                startDate = startDate,
                days = lineChartFilterValue.days
            )
//        }

    }
}