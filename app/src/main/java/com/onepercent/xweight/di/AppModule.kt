package com.onepercent.xweight.di

import android.content.Context
import androidx.room.Room
import com.onepercent.weight_datasource.cache.WeightMeasurementCacheDataSource
import com.onepercent.weight_interactors.*
import com.onepercent.xweight.room.XweightDatabase
import com.onepercent.xweight.room.WeightMeasurementCacheDataSourceImpl
import com.onepercent.xweight.room.WeightMeasurementDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideXweightDatabase(@ApplicationContext context: Context): XweightDatabase {
        return Room
            .databaseBuilder(
                context,
                XweightDatabase::class.java,
                XweightDatabase.DATABASE_NAME
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideWeightMeasurementDao(xweightDatabase: XweightDatabase): WeightMeasurementDao =
        xweightDatabase.weightMeasurementsDao()

    @Provides
    @Singleton
    fun provideWeightMeasurementCacheDataSource(
        weightMeasurementDao: WeightMeasurementDao
    ): WeightMeasurementCacheDataSource =
        WeightMeasurementCacheDataSourceImpl(weightMeasurementDao)

    @Provides
    @Singleton
    fun provideWeightInteractors(
        weightMeasurementCacheDataSource: WeightMeasurementCacheDataSource
    ): WeightInteractors {
        return WeightInteractors(
            InsertWeightMeasurement(weightMeasurementCacheDataSource),
            GetAllMeasurements(weightMeasurementCacheDataSource),
            GetLastMeasurement(weightMeasurementCacheDataSource),
            DeleteWeightMeasurement(weightMeasurementCacheDataSource)
        )
    }
}