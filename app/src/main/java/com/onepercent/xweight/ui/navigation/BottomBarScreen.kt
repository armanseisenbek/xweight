package com.onepercent.xweight.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Filled.Home
    )

    object History: BottomBarScreen(
        route = "history",
        title = "History",
        icon = Icons.Filled.List
    )
}