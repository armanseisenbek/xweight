package com.onepercent.xweight.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onepercent.xweight.weight.weight_datasource.cache.room.WeightMeasurementEntity
import com.onepercent.xweight.weight.weight_datasource.cache.room.WeightMeasurementDao

@Database(
    entities = [
        WeightMeasurementEntity::class
    ],
    version = 1
)
abstract class XweightDatabase : RoomDatabase() {

    abstract fun weightMeasurementsDao(): WeightMeasurementDao

    companion object{
        const val DATABASE_NAME: String = "xweight_db"
    }
}