package com.onepercent.xweight.weight.weight_interactors

import com.onepercent.xweight.weight.weight_domain.WeightMeasurement


class SortMeasurements {

    fun execute(
        unsortedList: List<WeightMeasurement>
    ): List<WeightMeasurement> {
        var sortedMeasurements: MutableList<WeightMeasurement> =
            unsortedList
                .sortedByDescending { it.date }
                .toMutableList()

        return sortedMeasurements
    }
}