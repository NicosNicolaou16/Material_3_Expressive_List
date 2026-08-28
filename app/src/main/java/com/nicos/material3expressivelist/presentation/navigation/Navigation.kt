@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.nicos.material3expressivelist.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.nicos.material3expressivelist.presentation.floating_toolbar_screen.FloatingToolbarRoot
import com.nicos.material3expressivelist.presentation.floating_toolbar_with_list_screen.FloatingToolbarWithListRoot
import com.nicos.material3expressivelist.presentation.buttons_group_screen.ButtonGroupsRoot
import com.nicos.material3expressivelist.presentation.expressive_list_screen.ExpressiveListRoot
import com.nicos.material3expressivelist.presentation.fab_menu_screen.FabMenuRoot
import com.nicos.material3expressivelist.presentation.fab_menu_with_list_screen.FabMenuWithListRoot
import com.nicos.material3expressivelist.presentation.loading_progress_indicator_screen.LoadingProgressIndicatorRoot
import com.nicos.material3expressivelist.presentation.navigation.navigation_3.Navigator
import com.nicos.material3expressivelist.presentation.navigation.navigation_3.navigationState
import com.nicos.material3expressivelist.presentation.slider_screen.SliderRoot
import com.nicos.material3expressivelist.presentation.split_buttons_screen.SplitButtonsRoot

@Composable
fun Navigation(innerPadding: PaddingValues) {
    // Navigation 3
    val navigationState = ExpressiveListScreen.navigationState()

    val navigator = remember { Navigator(navigationState) }

    val sceneStrategy = rememberListDetailSceneStrategy<NavKey>()

    val entryDecorators: List<NavEntryDecorator<NavKey>> = listOf(
        // Good practice to also include state saving for scroll positions, etc.
        rememberSaveableStateHolderNavEntryDecorator(),
        // This is the key line:
        rememberViewModelStoreNavEntryDecorator(),
    )

    NavDisplay(
        backStack = navigationState.stacksInUse,
        onBack = {
            navigator.goBack()
        },
        sceneStrategies = listOf(sceneStrategy),
        entryDecorators = entryDecorators,
        entryProvider = entryProvider {
            entry<ExpressiveListScreen> {
                ExpressiveListRoot(
                    navigator = navigator
                )
            }

            entry<ButtonGroupsScreen> {
                ButtonGroupsRoot(
                    navigator = navigator
                )
            }

            entry<SplitButtonsScreen> {
                SplitButtonsRoot(
                    navigator = navigator
                )
            }

            entry<LoadingProgressIndicatorScreen> {
                LoadingProgressIndicatorRoot(
                    navigator = navigator
                )
            }

            entry<FabMenuWithListScreen> {
                FabMenuWithListRoot(
                    navigator = navigator
                )
            }

            entry<FabMenuScreen> {
                FabMenuRoot(
                    navigator = navigator
                )
            }

            entry<FloatingToolBarWithListScreen> {
                FloatingToolbarWithListRoot(
                    navigator = navigator
                )
            }

            entry<FloatingToolBarScreen> {
                FloatingToolbarRoot(
                    navigator = navigator
                )
            }

            entry<SliderScreen> {
                SliderRoot(
                    navigator = navigator
                )
            }
        })
}