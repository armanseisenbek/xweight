package com.onepercent.xweight.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.onepercent.core.domain.ProgressBarState
import com.onepercent.core.domain.Queue
import com.onepercent.core.domain.UIComponent

/**
 * @param queue: Dialogs
 * @param content: The content of the UI.
 */
@Composable
fun DefaultScreenUI(
    queue: Queue<UIComponent> = Queue(mutableListOf()),
    onRemoveHeadFromQueue: () -> Unit,
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    content: @Composable () -> Unit,
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ){
        content()

        ProcessMessageQueue(
            messageQueue = queue,
            onRemoveHeadFromQueue = onRemoveHeadFromQueue
        )

        if(progressBarState is ProgressBarState.Loading){
            CircularIndeterminateProgressBar()
        }

    }
}
