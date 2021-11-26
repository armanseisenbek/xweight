package com.onepercent.xweight.ui.history.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HistoryScreenTopBar() {
    TopAppBar(
        title = {
            Text(text = "History")
        },
        actions = {

        }
    )
}

@Preview
@Composable
fun HistoryScreenTopBarPreview() {
    HistoryScreenTopBar()
}