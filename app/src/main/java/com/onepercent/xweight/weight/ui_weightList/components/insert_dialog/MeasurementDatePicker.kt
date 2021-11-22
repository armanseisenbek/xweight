package com.onepercent.xweight.weight.ui_weightList.components.insert_dialog

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun MeasurementDatePicker(
    context: Context,
    onPickDate: (Long) -> Unit,
    pickedDate: Long
) {

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = pickedDate

    val calendarYear = remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    val calendarMonth = remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    val calendarDay = remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    val stringDate = remember { mutableStateOf("${calendarDay.value}/${calendarMonth.value+1}/${calendarYear.value}") }

    val datePickerDialog = DatePickerDialog(
        context,
        { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            stringDate.value = "$dayOfMonth/${month+1}/$year"
            calendarYear.value = year
            calendarMonth.value = month
            calendarDay.value = dayOfMonth

            calendar.set(Calendar.DAY_OF_MONTH, datePicker.dayOfMonth)
            calendar.set(Calendar.MONTH, datePicker.month)
            calendar.set(Calendar.YEAR, datePicker.year)

            onPickDate(calendar.timeInMillis)
        },
        calendarYear.value,
        calendarMonth.value,
        calendarDay.value
    )

    // sets maximum date to today, so user can't pick future dates
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() }
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        content = { Text(text = stringDate.value) }
    )

}