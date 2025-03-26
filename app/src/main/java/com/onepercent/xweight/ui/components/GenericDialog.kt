package com.onepercent.xweight.ui.components

import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    onRemoveHeadFromQueue: () -> Unit,
) {
    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onRemoveHeadFromQueue()
        },
        content = {
            Text(text = title)
            if (description != null) {
                Text(text = description)
            }
        }
    )
}
