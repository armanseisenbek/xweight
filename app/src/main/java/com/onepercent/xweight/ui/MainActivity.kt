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
import com.onepercent.xweight.profile.ui_profile.ProfileScreen
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
                        startDestination = Screen.WeightList.route,
                        builder = {
                            addWeightList(navController)
                            addProfileScreen()
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
            events = weightListViewModel::onTriggerEvent

        )
    }
}

fun NavGraphBuilder.addProfileScreen(

) {
    composable(
        route = Screen.ProfileScreen.route
    ) {
        ProfileScreen()
    }
}