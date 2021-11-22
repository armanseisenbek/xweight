package com.onepercent.xweight.weight.weight_datasource.cache

import com.onepercent.xweight.weight.weight_datasource.cache.room.WeightMeasurementDaoService
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement

class WeightMeasurementCacheDataSourceImpl
constructor(
    private val weightMeasurementDaoService: WeightMeasurementDaoService
) : WeightMeasurementCacheDataSource {

    override suspend fun insertMeasurement(weightMeasurement: WeightMeasurement): Long {
        return weightMeasurementDaoService.insertMeasurement(weightMeasurement)
    }

    override suspend fun deleteMeasurement(weightMeasurement: WeightMeasurement): Int {
        return weightMeasurementDaoService.deleteMeasurement(weightMeasurement)
    }

    override suspend fun updateMeasurement(weightMeasurement: WeightMeasurement): Int {
        return weightMeasurementDaoService.updateMeasurement(weightMeasurement)
    }

    override suspend fun loadAllWeightMeasurements(): List<WeightMeasurement> {
        return weightMeasurementDaoService.loadAllWeightMeasurements()
    }

    override suspend fun getMeasurement(id: Long): WeightMeasurement {
        return weightMeasurementDaoService.getMeasurement(id)
    }

    override suspend fun getLastMeasurement(): WeightMeasurement? {
        return weightMeasurementDaoService.getLastMeasurement()
    }

    override suspend fun getMeasurementByDate(date: Long): WeightMeasurement? {
        return weightMeasurementDaoService.getMeasurementByDate(date)
    }
}