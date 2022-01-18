package com.onepercent.xweight.room

import com.onepercent.weight_domain.WeightMeasurement

fun WeightMeasurement.mapToEntity() : WeightMeasurementEntity {
    return WeightMeasurementEntity(
        id = this.id,
        weight = this.weight,
        date = this.date
    )
}

fun WeightMeasurementEntity.mapFromEntity() : WeightMeasurement {
    return WeightMeasurement(
        id = this.id,
        weight = this.weight,
        date = this.date
    )
}