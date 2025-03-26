package com.onepercent.xweight.ui.main.components.fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import com.onepercent.xweight.ui.theme.XweightTheme

@Composable
fun MainScreenFab(
    onClick: () -> Unit
) {

    FloatingActionButton(
        onClick = {
            onClick()
        },
        content = {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null
            )
        }
    )

}

@Preview
@Composable
fun MainScreenFabPreview() {
    XweightTheme {
        MainScreenFab {}
    }
}