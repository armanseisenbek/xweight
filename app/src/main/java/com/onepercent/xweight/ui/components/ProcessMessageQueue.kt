package com.onepercent.xweight.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.onepercent.core.domain.Queue
import com.onepercent.core.domain.UIComponent

@Composable
fun ProcessMessageQueue(
    messageQueue: Queue<UIComponent>,
    onRemoveHeadFromQueue: () -> Unit
) {
    // process the queue
    if(!messageQueue.isEmpty()){
        messageQueue.peek()?.let { uiComponent ->

            when (uiComponent) {
                is UIComponent.Dialog -> {
                    GenericDialog(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        title = uiComponent.title,
                        description = uiComponent.description,
                        onRemoveHeadFromQueue = onRemoveHeadFromQueue
                    )
                }
                is UIComponent.Toast -> {
                    Toast.makeText(
                        LocalContext.current,
                        uiComponent.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    onRemoveHeadFromQueue()
                }
            }
        }
    }
}