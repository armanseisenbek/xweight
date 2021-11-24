package com.onepercent.xweight.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onepercent.xweight.home.ui_homeScreen.HomeScreen
import com.onepercent.xweight.home.ui_homeScreen.HomeScreenViewModel
import com.onepercent.xweight.ui.navigation.Screen
import com.onepercent.xweight.ui.theme.XweightTheme
import com.onepercent.xweight.weight.ui_weightList.ui.WeightList
import com.onepercent.xweight.weight.ui_weightList.ui.WeightListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XweightTheme {

                BoxWithConstraints {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        builder = {
                            addWeightList(navController)
                            addHomeScreen(navController)
                        }
                    )
                }

            }
        }
    }
}

fun NavGraphBuilder.addWeightList(
    navController: NavController
) {
    composable(
        route = Screen.WeightList.route
    ) {
        val weightListViewModel: WeightListViewModel = hiltViewModel()

        WeightList(
            state = weightListViewModel.state.value,
            events = weightListViewModel::onTriggerEvent,
            navigateToHomeScreen = {
                navController.navigate(Screen.HomeScreen.route)
            }
        )
    }
}

fun NavGraphBuilder.addHomeScreen(
    navController: NavController
) {
    composable(
        route = Screen.HomeScreen.route
    ) {
        val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
        HomeScreen(
            state = homeScreenViewModel.state.value,
            events = homeScreenViewModel::onTriggerEvent,
            navigateToHistoryScreen = {
                navController.navigate(Screen.WeightList.route)
            }
        )
    }
}