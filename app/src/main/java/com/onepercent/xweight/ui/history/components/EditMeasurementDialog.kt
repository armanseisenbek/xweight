package com.onepercent.xweight.ui.history.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onepercent.weight_domain.WeightMeasurement
import com.onepercent.xweight.ui.components.MeasurementNumberPicker
import com.onepercent.xweight.ui.theme.XweightTheme
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMeasurementDialog(
    onInsertWeightMeasurement: (WeightMeasurement) -> Unit,
    onDeleteMeasurement: (Long) -> Unit,
    onCloseDialog: () -> Unit,
    onPickValue: (Double) -> Unit,
    measurementDate: Long,
    measurementValue: Double
) {

    BasicAlertDialog(
        onDismissRequest = { onCloseDialog() },
        content = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                content = { Text(text = "Edit Weight") }
            )
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