package com.onepercent.xweight.ui.history.di

import com.onepercent.xweight.weight.weight_interactors.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeightListModule {

    @Provides
    @Singleton
    fun provideInsertMeasurement(
        interactors: WeightInteractors
    ) : InsertWeightMeasurement = interactors.insertWeightMeasurement

    @Provides
    @Singleton
    fun provideGetAllMeasurements(
        interactors: WeightInteractors
    ) : GetAllMeasurements = interactors.getAllMeasurements

    @Provides
    @Singleton
    fun provideGetLastMeasurement(
        interactors: WeightInteractors
    ) : GetLastMeasurement = interactors.getLastMeasurement

    @Provides
    @Singleton
    fun provideSortMeasurements(
        interactors: WeightInteractors
    ) : SortMeasurements = interactors.sortMeasurements

    @Provides
    @Singleton
    fun provideDeleteMeasurement(
        interactors: WeightInteractors
    ) : DeleteMeasurement = interactors.deleteMeasurement
}