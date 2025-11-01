package com.nicos.material3expressivelist.presentation.navigation

import kotlinx.serialization.Serializable

sealed class ScreenRoutes {

    @Serializable
    data object ExpressiveListScreen : ScreenRoutes()

    @Serializable
    data object ButtonGroupsScreen : ScreenRoutes()

    @Serializable
    data object SplitButtonsScreen : ScreenRoutes()

    @Serializable
    data object LoadingProgressIndicatorScreen : ScreenRoutes()

    @Serializable
    data object FabMenuWithListScreen : ScreenRoutes()

    @Serializable
    data object FabMenuScreen : ScreenRoutes()

    @Serializable
    data object FloatingToolBarWithListScreen : ScreenRoutes()

    @Serializable
    data object FloatingToolBarScreen : ScreenRoutes()
}