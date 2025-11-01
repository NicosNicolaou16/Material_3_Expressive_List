@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.nicos.material3expressivelist.presentation.expressive_list_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.copy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nicos.material3expressivelist.presentation.expressive_list_screen.models.ExpressiveListDataModel
import com.nicos.material3expressivelist.presentation.navigation.ScreenRoutes
import com.nicos.material3expressivelist.ui.theme.Purple80

@Composable
fun ExpressiveListRoot(
    navController: NavHostController,
) {
    Scaffold { paddingValues ->
        ExpressiveListScreen(
            paddingValues = paddingValues,
            screen = { navController.navigate(it) }
        )
    }
}

@Composable
fun ExpressiveListScreen(
    paddingValues: PaddingValues,
    state: ExpressiveListViewModel = hiltViewModel(),
    screen: (ScreenRoutes) -> Unit
) {
    val collectAsState = state.items.collectAsState().value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        label = "scale"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 40.dp, top = 32.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.shadowElevation = 15.dp.toPx()
                this.spotShadowColor = Color.Black.copy(alpha = 0.4f)
                this.ambientShadowColor = Color.Black.copy(alpha = 0.3f)
            },
    ) {
        ElevatedButton(
            modifier = Modifier
                .height(height = 80.dp)
                .fillMaxWidth(),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(containerColor = Purple80),
            shape = RoundedCornerShape(15.dp),
            elevation = buttonElevation(
                defaultElevation = 15.dp,
                pressedElevation = 2.dp
            ),
            /*modifierWithShadow = Modifier.graphicsLayer {
                this.shadowElevation = 15.dp.toPx() // Ensure this matches defaultElevation
                this.spotShadowColor = Color.Black.copy(alpha = 0.4f) // Darker spot shadow
                this.ambientShadowColor = Color.Black.copy(alpha = 0.2f) // Darker ambient shadow
            },*/
            onClick = {
                //screen(item.screenRoutes)
            }) {
            Text(
                item.title,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}