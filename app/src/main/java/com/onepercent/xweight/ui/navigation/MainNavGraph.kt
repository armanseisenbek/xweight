package com.onepercent.xweight.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.onepercent.xweight.ui.history.HistoryScreen
import com.onepercent.xweight.ui.history.HistoryScreenViewModel
import com.onepercent.xweight.ui.main.MainScreen
import com.onepercent.xweight.ui.main.MainScreenViewModel
import com.onepercent.xweight.ui.theme.XweightTheme

@ExperimentalAnimationApi
@Composable
fun MainNavGraph() {
    XweightTheme {
        BoxWithConstraints {
            val navController = rememberAnimatedNavController()

            AnimatedNavHost(
                navController = navController,
                startDestination = Screen.Main.route,
                builder = {
                    mainScreen(
                        navController = navController
                    )
                    historyScreen(height = constraints.maxHeight)
                }
            )
        }
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.mainScreen(
    navController: NavController
) {
    composable(
        route = Screen.Main.route
    ) {
        val mainScreenViewModel: MainScreenViewModel = hiltViewModel()

        MainScreen(
            state = mainScreenViewModel.state.value,
            events = mainScreenViewModel::onTriggerEvent,
            navigateToHistoryScreen = {
                navController.navigate(Screen.History.route)
            }
        )
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.historyScreen(
    height: Int
) {
    composable(
        route = Screen.History.route,
        enterTransition = {
            slideInVertically(
                initialOffsetY = { height },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) //+ fadeIn(animationSpec = tween(10000))
        },
        popExitTransition = {
            slideOutVertically(
                targetOffsetY = { height },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) //+ fadeOut(animationSpec = tween(300))
        }
    ) {
        val historyScreenViewModel: HistoryScreenViewModel = hiltViewModel()

        HistoryScreen(
            state = historyScreenViewModel.state.value,
            events = historyScreenViewModel::onTriggerEvent,
        )
    }
}