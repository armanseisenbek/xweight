package com.onepercent.xweight.weight.weight_datasource.cache.room

import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

interface WeightMeasurementDaoService {

    suspend fun insertMeasurement(weightMeasurement: WeightMeasurement): Long

    suspend fun deleteMeasurement(weightMeasurement: WeightMeasurement): Int

    suspend fun updateMeasurement(weightMeasurement: WeightMeasurement): Int

    suspend fun getMeasurement(id: Long): WeightMeasurement

    suspend fun loadAllWeightMeasurements(): List<WeightMeasurement>

    suspend fun getLastMeasurement(): WeightMeasurement?

    suspend fun getMeasurementByDate(date: Long): WeightMeasurement?
}