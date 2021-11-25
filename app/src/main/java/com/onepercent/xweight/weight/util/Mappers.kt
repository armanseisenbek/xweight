package com.onepercent.xweight.weight.util

import com.onepercent.xweight.weight.weight_datasource.cache.WeightMeasurementEntity
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

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