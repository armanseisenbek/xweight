package com.onepercent.xweight.weight.weight_interactors

data class WeightInteractors(
    val insertWeightMeasurement: InsertWeightMeasurement,
    val getAllMeasurements: GetAllMeasurements,
    val getLastMeasurement: GetLastMeasurement,
    val sortMeasurements: SortMeasurements,
    val deleteMeasurement: DeleteMeasurement
)