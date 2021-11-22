package com.onepercent.xweight.weight.weight_interactors

import com.onepercent.xweight.core.domain.DataState
import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.weight.weight_datasource.cache.WeightMeasurementCacheDataSource
import com.onepercent.xweight.weight.weight_domain.WeightMeasurement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertWeightMeasurement(
    private val weightMeasurementCacheDataSource: WeightMeasurementCacheDataSource
) {

    fun execute(
        weight: Double,
        date: Long
    ): Flow<DataState<WeightMeasurement>> = flow {

        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            // dividing date to one day and removing remainder, so we can enter only one
            // measurement in a given day
            val oneDayInMillis = 86400000
            val remainder = date % oneDayInMillis
            val correctedDate = date - remainder

            // searching measurement with same date
            val oldMeasurement = weightMeasurementCacheDataSource.getMeasurementByDate(correctedDate)

            // update if measurement on this date already exists
            // insert new if it's not
            val id = if (oldMeasurement != null) {
                weightMeasurementCacheDataSource.updateMeasurement(
                    WeightMeasurement(
                        id = oldMeasurement.id,
                        weight = weight,
                        date = correctedDate
                    )
                )

                // return id
                oldMeasurement.id
            } else {
                weightMeasurementCacheDataSource.insertMeasurement(
                    WeightMeasurement(
                        weight = weight,
                        date = correctedDate
                    )
                )
            }

            val cachedMeasurement = weightMeasurementCacheDataSource.getMeasurement(id)

            emit(DataState.Data(cachedMeasurement))

        } catch (e: Exception) {
            e.printStackTrace()

            emit(
                DataState.Response<WeightMeasurement>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        }

        finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }

    }
}