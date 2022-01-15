package com.onepercent.xweight.weight.weight_interactors

import com.onepercent.core.domain.DataState
import com.onepercent.core.domain.ProgressBarState
import com.onepercent.core.domain.UIComponent
import com.onepercent.weight_domain.WeightMeasurement
import com.onepercent.xweight.weight.weight_datasource.cache.WeightMeasurementCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllMeasurements(
    private val weightMeasurementCacheDataSource: WeightMeasurementCacheDataSource
) {

    fun execute(): Flow<DataState<List<WeightMeasurement>>> = flow {

        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            val cachedMeasurements = weightMeasurementCacheDataSource.loadAllWeightMeasurements()

            //kotlinx.coroutines.delay(1000)
            emit(DataState.Data(cachedMeasurements))

        } catch (e: Exception) {
            e.printStackTrace()

            emit(
                DataState.Response<List<WeightMeasurement>>(
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