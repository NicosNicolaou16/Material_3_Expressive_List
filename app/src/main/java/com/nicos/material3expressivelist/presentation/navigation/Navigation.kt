package com.nicos.material3expressivelist.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nick.samplecomposeandhilt.compose.main_bottom_navigation_view.processes.processes_screen.screens.floating_toolbar.FloatingToolbarRoot
import com.nick.samplecomposeandhilt.compose.main_bottom_navigation_view.processes.processes_screen.screens.floating_toolbar_with_list.FloatingToolbarWithListRoot
import com.nicos.material3expressivelist.presentation.buttons_group_screen.ButtonGroupsRoot
import com.nicos.material3expressivelist.presentation.expressive_list_screen.ExpressiveListRoot
import com.nicos.material3expressivelist.presentation.fab_menu.FabMenuRoot
import com.nicos.material3expressivelist.presentation.fab_menu_with_list.FabMenuWithListRoot
import com.nicos.material3expressivelist.presentation.loading_progress_indicator.LoadingProgressIndicatorRoot
import com.nicos.material3expressivelist.presentation.split_buttons_screen.SplitButtonsRoot

@Composable
fun Navigation(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.ExpressiveListScreen,
    ) {
        composable<ScreenRoutes.ExpressiveListScreen> {
            ExpressiveListRoot(
                navController = navController
            )
        }

        composable<ScreenRoutes.ButtonGroupsScreen> {
            ButtonGroupsRoot(
                navController = navController
            )
        }

        composable<ScreenRoutes.SplitButtonsScreen> {
            SplitButtonsRoot(
                navController = navController
            )
        }

        composable<ScreenRoutes.LoadingProgressIndicatorScreen> {
            LoadingProgressIndicatorRoot(
                navController = navController
            )
        }

        composable<ScreenRoutes.FabMenuWithListScreen> {
            FabMenuWithListRoot(
                navController = navController
            )
        }

        composable<ScreenRoutes.FabMenuScreen> {
            FabMenuRoot(
                navController = navController
            )
        }

        composable<ScreenRoutes.FloatingToolBarWithListScreen> {
            FloatingToolbarWithListRoot(
                navController = navController
            )
        }

        composable<ScreenRoutes.FloatingToolBarScreen> {
            FloatingToolbarRoot(
                navController = navController
            )
        }
    }
}