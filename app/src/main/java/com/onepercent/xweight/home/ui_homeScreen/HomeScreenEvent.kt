package com.onepercent.xweight.home.ui_homeScreen

sealed class HomeScreenEvent {

    object GetAllMeasurements: HomeScreenEvent()

    object OnRemoveHeadFromQueue: HomeScreenEvent()
}