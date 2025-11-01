package com.nicos.material3expressivelist.presentation.expressive_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nicos.material3expressivelist.presentation.navigation.ScreenRoutes
import com.nicos.material3expressivelist.ui.theme.Purple80

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
    val collectAsState = state.items.collectAsState().value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        items(
            items = collectAsState,
            key = { it.title }
        ) {
            ExpressiveListItem(item = it, screen = screen)
        }
    }
}

@Composable
fun ExpressiveListItem(
    item: ExpressiveListDataModel,
    screen: (ScreenRoutes) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp, top = 32.dp)
    ) {
        ElevatedButton(
            modifier = Modifier
                .height(height = 60.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Purple80),
            elevation = buttonElevation(
                defaultElevation = 9.dp
            ),
            onClick = {
                screen(item.screenRoutes)
            }) {
            Text(
                item.title, style = TextStyle(
                    fontSize = 19.sp,
                )
            )
        }
    }
}