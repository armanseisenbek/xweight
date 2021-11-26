package com.onepercent.xweight.ui.history.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

@Composable
fun MeasurementHistoryList(
    modifier: Modifier = Modifier,
    weightMeasurements: List<WeightMeasurement>,
    onSelectMeasurement: (Long, Double) -> Unit,
) {
    LazyColumn(modifier = modifier) {

        itemsIndexed(weightMeasurements) { index: Int, item: WeightMeasurement ->
            WeightListItem(
                weightMeasurement = item,
                previousMeasurement = if (index + 1 < weightMeasurements.size) {
                    weightMeasurements[index+1]
                } else {
                    null
                },
                onSelectMeasurement = onSelectMeasurement
            )
        }

    }
}

@Preview
@Composable
fun MeasurementHistoryListPreview() {
    MeasurementHistoryList(
        weightMeasurements = listOf(
            WeightMeasurement(1, 67.6, 0),
            WeightMeasurement(1, 66.6, 0),
            WeightMeasurement(1, 66.8, 0),
            WeightMeasurement(1, 65.6, 0),
        ),
        onSelectMeasurement = { _, _ -> }
    )
}