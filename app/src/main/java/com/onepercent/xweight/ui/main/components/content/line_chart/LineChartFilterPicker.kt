package com.onepercent.xweight.ui.main.components.content.line_chart

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun LineChartFilterPicker(
    lineChartFilterValue: LineChartFilter,
    pickLineChartFilterValue: (LineChartFilter) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    val lineChartFilterOptions = remember {
        listOf(
            LineChartFilter.TwoWeeks,
            LineChartFilter.Month,
            LineChartFilter.NinetyDays,
            LineChartFilter.Year
        )
    }

    TextButton(
        modifier = Modifier.padding(horizontal = 5.dp),
        onClick = {
            expanded = !expanded
        },
        shape = MaterialTheme.shapes.large,
        content = {
            Text(text = stringResource(lineChartFilterValue.label).uppercase())
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        },
        content = {
            lineChartFilterOptions.forEach {
                DropdownMenuItem(
                    onClick = {
                        pickLineChartFilterValue(it)
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