package com.onepercent.xweight.weight.weight_interactors

import com.onepercent.weight_domain.WeightMeasurement
import com.onepercent.xweight.core.domain.DataState
import com.onepercent.xweight.core.domain.ProgressBarState
import com.onepercent.xweight.core.domain.UIComponent
import com.onepercent.xweight.weight.weight_datasource.cache.WeightMeasurementCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteMeasurement(
    private val weightMeasurementCacheDataSource: WeightMeasurementCacheDataSource
) {

    fun execute(
        date: Long
    ): Flow<DataState<WeightMeasurement>> = flow {

        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            // getting measurement from cache
            val measurement = weightMeasurementCacheDataSource.getMeasurementByDate(date)

            if (measurement != null) {
                weightMeasurementCacheDataSource.deleteMeasurement(measurement)

                emit(
                    DataState.Response<WeightMeasurement>(
                        uiComponent = UIComponent.Toast("Measurement successfully deleted")
                    )
                )
            } else {
                emit(
                    DataState.Response<WeightMeasurement>(
                        uiComponent = UIComponent.Dialog(
                            title = "Measurement was not found",
                            description = "Something went wrong when deleting measurement"
                        )
                    )
                )
            }

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