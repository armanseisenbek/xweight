package com.onepercent.xweight.di

import android.content.Context
import androidx.room.Room
import com.onepercent.xweight.core.XweightDatabase
import com.onepercent.xweight.weight.weight_datasource.cache.WeightMeasurementCacheDataSource
import com.onepercent.xweight.weight.weight_datasource.cache.WeightMeasurementCacheDataSourceImpl
import com.onepercent.xweight.weight.weight_datasource.cache.WeightMeasurementDao
import com.onepercent.xweight.weight.weight_interactors.*
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
            SortMeasurements(),
            DeleteMeasurement(weightMeasurementCacheDataSource)
        )
    }
}