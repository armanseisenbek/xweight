package com.onepercent.xweight.core.domain

sealed class ProgressBarState {

    object Loading: ProgressBarState()
    object Idle: ProgressBarState()
}