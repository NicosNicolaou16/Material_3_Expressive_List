package com.nicos.material3expressivelist.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nicos.material3expressivelist.presentation.expressive_list_screen.ExpressiveListRoot

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

        /*composable<ScreenRoutes.ButtonGroupsScreen> {
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
        }*/
    }
}