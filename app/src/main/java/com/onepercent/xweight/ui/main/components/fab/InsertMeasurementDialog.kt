package com.onepercent.xweight.ui.main.components.fab

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.onepercent.weight_domain.WeightMeasurement

import com.onepercent.xweight.ui.theme.XweightTheme
import com.onepercent.xweight.ui.components.MeasurementNumberPicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertMeasurementDialog(
    onInsertWeightMeasurement: (WeightMeasurement) -> Unit,
    onPickDate: (Long) -> Unit,
    onPickValue: (Double) -> Unit,
    onCloseDialog: () -> Unit,
    measurementDate: Long,
    measurementValue: Double
) {

    BasicAlertDialog(
        onDismissRequest = { onCloseDialog() },

        content = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                content = { Text(text = "Insert Weight") }
            )

            // Date picker
            MeasurementDatePicker(
                onPickDate = onPickDate,
                pickedDate = measurementDate
            )

            // Number picker
            MeasurementNumberPicker(
                onPickValue = onPickValue,
                lastMeasurement = measurementValue
            )

            //  DONE Buttons
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onInsertWeightMeasurement(
                        WeightMeasurement(weight = measurementValue, date = measurementDate)
                    )
                    onCloseDialog()
                },
                content = { Text(modifier = Modifier.padding(10.dp), text = "DONE") },
                shape = MaterialTheme.shapes.large
            )

        }
    )
}

@Preview
@Composable
fun InsertMeasurementDialogPreview() {
    XweightTheme {
        InsertMeasurementDialog(
            onInsertWeightMeasurement = {},
            onPickValue = {},
            onPickDate = {},
            onCloseDialog = {},
            measurementDate = 0,
            measurementValue = 67.5
        )
    }
}