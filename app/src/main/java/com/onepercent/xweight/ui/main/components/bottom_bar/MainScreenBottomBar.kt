package com.onepercent.xweight.ui.main.components.bottom_bar

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import com.onepercent.xweight.ui.navigation.Screen

@Composable
fun MainScreenBottomBar(navigateToHistoryScreen: () -> Unit) {
    BottomAppBar(
        cutoutShape = CircleShape,
        content = {
            IconButton(
                onClick = {
                    navigateToHistoryScreen()
                },
                content = {
                    Icon(
                        imageVector = Screen.History.icon,
                        contentDescription = Screen.History.title
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun MainScreenBottomBarPreview() {
    MainScreenBottomBar {}
}