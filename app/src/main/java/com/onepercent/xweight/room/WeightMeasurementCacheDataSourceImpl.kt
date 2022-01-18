package com.onepercent.xweight.room

import com.onepercent.weight_datasource.cache.WeightMeasurementCacheDataSource
import com.onepercent.weight_domain.WeightMeasurement

class WeightMeasurementCacheDataSourceImpl
constructor(
    private val weightMeasurementDao: WeightMeasurementDao
) : WeightMeasurementCacheDataSource {

    override suspend fun insertMeasurement(weightMeasurement: WeightMeasurement): Long {
        return weightMeasurementDao.insertMeasurement(weightMeasurement.mapToEntity())
    }

    override suspend fun deleteMeasurement(weightMeasurement: WeightMeasurement): Int {
        return weightMeasurementDao.deleteMeasurement(weightMeasurement.mapToEntity())
    }

    override suspend fun updateMeasurement(weightMeasurement: WeightMeasurement): Int {
        return weightMeasurementDao.updateMeasurement(weightMeasurement.mapToEntity())
    }

    override suspend fun loadAllWeightMeasurements(): List<WeightMeasurement> {
        return weightMeasurementDao.loadAllWeightMeasurements().map {
            it.mapFromEntity()
        }
    }

    override suspend fun getMeasurement(id: Long): WeightMeasurement {
        return weightMeasurementDao.getMeasurement(id).mapFromEntity()
    }

    override suspend fun getLastMeasurement(): WeightMeasurement? {
        val measurement = weightMeasurementDao.getLastMeasurement()
        return if (measurement != null) measurement.mapFromEntity() else null
    }

    override suspend fun getMeasurementByDate(date: Long): WeightMeasurement? {
        val measurement = weightMeasurementDao.getMeasurementByDate(date)
        return if (measurement != null) measurement.mapFromEntity() else null
    }
}