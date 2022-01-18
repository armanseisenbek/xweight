package com.onepercent.xweight.room

import androidx.room.*

@Dao
interface WeightMeasurementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeasurement(weightMeasurementEntity: WeightMeasurementEntity): Long

    @Delete
    suspend fun deleteMeasurement(weightMeasurementEntity: WeightMeasurementEntity): Int

    @Update
    suspend fun updateMeasurement(weightMeasurementEntity: WeightMeasurementEntity): Int

    @Query ("SELECT * FROM weight_measurements WHERE id = :id")
    suspend fun getMeasurement(id: Long): WeightMeasurementEntity

    @Query("SELECT * FROM weight_measurements")
    suspend fun loadAllWeightMeasurements(): List<WeightMeasurementEntity>

    @Query("SELECT * FROM weight_measurements ORDER BY date DESC LIMIT 1")
    suspend fun getLastMeasurement(): WeightMeasurementEntity?

    @Query("SELECT * FROM weight_measurements WHERE date = :date")
    suspend fun getMeasurementByDate(date: Long): WeightMeasurementEntity?
}