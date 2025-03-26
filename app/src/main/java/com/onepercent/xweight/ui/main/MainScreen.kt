package com.onepercent.xweight.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.onepercent.core.domain.UIComponentState
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
        content = { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                MainScreenContent(state = state, events = events)
            }
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
        floatingActionButtonPosition = FabPosition.End
    )
}