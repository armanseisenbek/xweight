package com.onepercent.xweight.ui.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {
    object WeightList: Screen("weightList", emptyList())

    object HomeScreen: Screen("homeScreen", emptyList())
}