package com.onepercent.xweight.ui.components

import android.widget.NumberPicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.onepercent.xweight.ui.theme.XweightTheme

@Composable
fun MeasurementNumberPicker(
    onPickValue: (Double) -> Unit,
    lastMeasurement: Double
) {

    // split latest double value to two numbers
    // example: 65.54 = first: 65, second: 54
    val measurementValueFirst = remember { mutableStateOf("$lastMeasurement".split(".")[0].toInt()) }
    val measurementValueSecond = remember { mutableStateOf("$lastMeasurement".split(".")[1].toInt()) }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            //.clickable { }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        AndroidView(
            factory = {
                NumberPicker(it).apply {
                    setOnValueChangedListener { numberPicker, oldValue, newValue ->
                        measurementValueFirst.value = newValue
                        onPickValue(newValue.toDouble() + (measurementValueSecond.value / 10.toDouble()))
                    }
                    minValue = 0
                    maxValue = 500
                    value = measurementValueFirst.value
                }
            }
        )

        Text(modifier = Modifier.padding(3.dp), text = ".")

        AndroidView(
            factory = {
                NumberPicker(it).apply {
                    setOnValueChangedListener { numberPicker, oldValue, newValue ->
                        measurementValueSecond.value = newValue
                        onPickValue(measurementValueFirst.value.toDouble() + (newValue / 10.toDouble()))
                    }
                    minValue = 0
                    maxValue = 9
                    value = measurementValueSecond.value
                }
            }
        )
        
        //Text( modifier = Modifier.padding(3.dp), text = "kg")
    }

}

@Preview
@Composable
fun MeasurementNumberPickerPreview() {
    XweightTheme {
        MeasurementNumberPicker(
            onPickValue = {},
            lastMeasurement = 67.5
        )
    }
}