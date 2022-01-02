package com.onepercent.xweight.ui.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable

import com.onepercent.xweight.core.domain.UIComponentState
import com.onepercent.xweight.ui.main.components.bottom_bar.MainScreenBottomBar
import com.onepercent.xweight.ui.main.components.fab.MainScreenFab
import com.onepercent.xweight.ui.main.components.content.MainScreenContent

@Composable
fun MainScreen(
    state: MainScreenState,
    events: (MainScreenEvent) -> Unit,
    navigateToHistoryScreen: () -> Unit
) {

    Scaffold(
        content = {
            MainScreenContent(state = state, events = events)
        },
        bottomBar = {
            MainScreenBottomBar(navigateToHistoryScreen = navigateToHistoryScreen)
        },
        floatingActionButton = {
            MainScreenFab {
                events(MainScreenEvent.GetLastMeasurement)
                events(MainScreenEvent.UpdateInsertDialogState(UIComponentState.Show))
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true
    )
}