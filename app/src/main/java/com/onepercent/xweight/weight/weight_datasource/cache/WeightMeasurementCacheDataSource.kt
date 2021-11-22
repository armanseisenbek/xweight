package com.onepercent.xweight.weight.weight_datasource.cache

import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

interface WeightMeasurementCacheDataSource {

    suspend fun insertMeasurement(weightMeasurement: WeightMeasurement): Long

    suspend fun deleteMeasurement(weightMeasurement: WeightMeasurement): Int

    suspend fun updateMeasurement(weightMeasurement: WeightMeasurement): Int

    suspend fun loadAllWeightMeasurements(): List<WeightMeasurement>

    suspend fun getMeasurement(id: Long): WeightMeasurement

    suspend fun getLastMeasurement(): WeightMeasurement?

    suspend fun getMeasurementByDate(date: Long): WeightMeasurement?
}