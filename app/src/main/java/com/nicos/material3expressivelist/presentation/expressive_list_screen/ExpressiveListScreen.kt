package com.nicos.material3expressivelist.presentation.expressive_list_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nicos.material3expressivelist.presentation.navigation.ScreenRoutes

@Composable
fun ExpressiveListRoot(
    navController: NavHostController,
) {
    Scaffold {
        ExpressiveListScreen(
            screen = { navController.navigate(it) }
        )
    }
}

@Composable
fun ExpressiveListScreen(
    state: ExpressiveListViewModel = hiltViewModel(),
    screen: (ScreenRoutes) -> Unit
) {
    LazyColumn {
        items(items = state.items.value) {
            ExpressiveListItem(item = it, screen = screen)
        }
    }
}

@Composable
fun ExpressiveListItem(
    item: ExpressiveListDataModel,
    screen: (ScreenRoutes) -> Unit
) {
    Button(onClick = {
        screen(item.screenRoutes)
    }) { Text(item.title) }
}