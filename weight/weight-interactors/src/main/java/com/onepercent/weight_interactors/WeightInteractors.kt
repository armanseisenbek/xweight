package com.onepercent.weight_interactors

data class WeightInteractors(
    val insertWeightMeasurement: InsertWeightMeasurement,
    val getAllMeasurements: GetAllMeasurements,
    val getLastMeasurement: GetLastMeasurement,
    val deleteMeasurement: DeleteWeightMeasurement
)