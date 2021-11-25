package com.onepercent.xweight.ui.home

sealed class HomeScreenEvent {

    object GetAllMeasurements: HomeScreenEvent()

    object OnRemoveHeadFromQueue: HomeScreenEvent()
}