package com.onepercent.xweight.weight.ui_weightList.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onepercent.xweight.ui.theme.XweightTheme
import com.onepercent.xweight.weight.ui_weightList.components.insert_dialog.MeasurementNumberPicker
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EditMeasurementDialog(
    onInsertWeightMeasurement: (WeightMeasurement) -> Unit,
    onDeleteMeasurement: (Long) -> Unit,
    onCloseDialog: () -> Unit,
    onPickValue: (Double) -> Unit,
    measurementDate: Long,
    measurementValue: Double
) {

    AlertDialog(
        onDismissRequest = { onCloseDialog() },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                content = { Text(text = "Edit Weight") }
            )
        },
        buttons = {

            // Date
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                content = {
                    Text(
                        text = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
                            .format(Date(measurementDate))
                    )
                }
            )

            // Number picker
            MeasurementNumberPicker(
                onPickValue = onPickValue,
                lastMeasurement = measurementValue
            )

            // Delete and Done buttons
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                content = {

                    TextButton(
                        onClick = {
                            onDeleteMeasurement(measurementDate)
                            onCloseDialog()
                        },
                        content = {
                            Text(modifier = Modifier.padding(5.dp), text = "DELETE")
                        }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(
                        modifier = Modifier,
                        onClick = {
                            onInsertWeightMeasurement(
                                WeightMeasurement(weight = measurementValue, date = measurementDate)
                            )
                            onCloseDialog()
                        },
                        content = {
                            Text(modifier = Modifier.padding(5.dp),text = "DONE")
                        },
                    )
                }
            )

        }
    )
}

@Preview
@Composable
fun EditMeasurementDialogPreview() {
    XweightTheme {
        EditMeasurementDialog(
            onInsertWeightMeasurement = {},
            onDeleteMeasurement = {},
            onPickValue = {},
            onCloseDialog = {},
            measurementDate = 0,
            measurementValue = 67.5
        )
    }
}