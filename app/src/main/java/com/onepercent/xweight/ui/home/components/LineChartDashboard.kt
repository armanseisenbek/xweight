package com.onepercent.xweight.ui.home.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.util.Constants.Companion.ONE_DAY_IN_MILLIS
import com.onepercent.xweight.core.util.formatWeight
import com.onepercent.xweight.ui.home.components.line_chart.LineChartFilter
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

@Composable
fun LineChartDashboard(
    progressBarState: ProgressBarState,
    weightMeasurements: List<WeightMeasurement>,
    lineChartFilterValue: LineChartFilter,
    pickLineChartFilter: (LineChartFilter) -> Unit,
) {

    val lineChartFilterOptions = remember {
        listOf(
            LineChartFilter.TwoWeeks,
            LineChartFilter.Month,
            LineChartFilter.NinetyDays,
            LineChartFilter.Year
        )
    }

    Column {

        var expanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = {
                Spacer(modifier = Modifier)
                Column {
                    OutlinedButton(
                        onClick = {
                            expanded = !expanded
                        },
                        shape = MaterialTheme.shapes.medium,
                        //border = BorderStroke(1.dp, Color.Black),
                        content = {
                            Text(text = stringResource(lineChartFilterValue.label).uppercase())
                        }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        content = {
                            lineChartFilterOptions.forEach {
                                DropdownMenuItem(
                                    onClick = {
                                        pickLineChartFilter(it)
                                        expanded = !expanded
                                    },
                                    content = {
                                        Text(text = stringResource(it.label))
                                    }
                                )
                            }
                        }
                    )
                }
            }
        )

        if (progressBarState != ProgressBarState.Loading) {

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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                content = {
                    Column {
                        Text(
                            text = rowText,
                            color = Color(0xFF494d50)
                        )
                    }
                }
            )

            LinearWeightChart(
                modifier = Modifier
                    .height(250.dp)
                    .padding(5.dp)
                    .fillMaxWidth()
                    //.border(1.dp, Color.Blue)
                    .padding(5.dp, 15.dp, 15.dp, 15.dp),
                measurements = filteredMeasurements,
                maxMeasurement = maxMeasurement,
                minMeasurement = minMeasurement,
                startDate = startDate,
                days = lineChartFilterValue.days
            )
        }

    }
}