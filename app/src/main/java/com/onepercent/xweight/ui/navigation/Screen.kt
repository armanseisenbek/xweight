package com.onepercent.xweight.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Main: Screen(
        route = "main",
        title = "Main",
        icon = Icons.Filled.Home
    )

    object Home: Screen(
        route = "home",
        title = "Home",
        icon = Icons.Filled.Home
    )

    object History: Screen(
        route = "history",
        title = "History",
        icon = Icons.Filled.List
    )
}