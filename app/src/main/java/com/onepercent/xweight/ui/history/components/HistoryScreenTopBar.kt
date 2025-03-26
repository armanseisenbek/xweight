package com.onepercent.xweight.ui.history.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
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