package com.onepercent.xweight.room

import androidx.room.Database
import androidx.room.RoomDatabase

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