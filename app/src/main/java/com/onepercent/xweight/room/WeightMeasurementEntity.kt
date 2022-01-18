package com.onepercent.xweight.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_measurements")
data class WeightMeasurementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val weight: Double,
    val date: Long
)