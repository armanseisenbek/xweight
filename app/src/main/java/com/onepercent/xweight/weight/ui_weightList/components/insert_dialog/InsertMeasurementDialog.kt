package com.onepercent.xweight.weight.ui_weightList.components.insert_dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onepercent.xweight.ui.theme.XweightTheme
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

@Composable
fun InsertMeasurementDialog(
    onInsertWeightMeasurement: (WeightMeasurement) -> Unit,
    onPickDate: (Long) -> Unit,
    onPickValue: (Double) -> Unit,
    onCloseDialog: () -> Unit,
    measurementDate: Long,
    measurementValue: Double
) {

    AlertDialog(
        onDismissRequest = { onCloseDialog() },

        title = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                content = { Text(text = "Insert Weight") }
            )
        },

        buttons = {

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