@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.nick.samplecomposeandhilt.compose.main_bottom_navigation_view.processes.processes_screen.screens.floating_toolbar_with_list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nicos.material3expressivelist.R
import com.nicos.material3expressivelist.presentation.CustomToolbar

@Composable
fun FloatingToolbarWithListRoot(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = stringResource(R.string.floating_toolbar_with_list),
                backButton = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        FloatingToolbarWithList(paddingValues = paddingValues)
    }
}

@SuppressLint("RememberInComposition")
@Composable
fun FloatingToolbarWithList(
    paddingValues: PaddingValues,
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val fabVisible by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    val focusRequester = FocusRequester()
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors(
        toolbarContainerColor = MaterialTheme.colorScheme.primary,
        toolbarContentColor = MaterialTheme.colorScheme.onPrimary,
    )
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            for (index in 0 until 100) {
                item {
                    Text(
                        text = stringResource(R.string.list_item, index),
                        modifier = Modifier
                            .clickable {}
                            .fillMaxWidth()
                            .padding(24.dp),
                    )
                }
            }
        }

        BackHandler(expanded) { expanded = false }

        HorizontalFloatingToolbar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .animateFloatingActionButton(
                    visible = fabVisible || expanded,
                    alignment = Alignment.BottomEnd,
                )
                .focusRequester(focusRequester),
            expanded = expanded,
            colors = vibrantColors,
            content = {
                IconButton(
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.settings),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings_24px),
                        contentDescription = "Settings",
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = {
                    Toast.makeText(context, context.getString(R.string.edit), Toast.LENGTH_SHORT)
                        .show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_24px),
                        contentDescription = stringResource(R.string.edit),
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(
                    enabled = true,
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.back),
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = stringResource(R.string.back)

                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(enabled = true, onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.processes), Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_processes),
                        contentDescription = stringResource(R.string.processes)
                    )
                }
            },
        )
    }
}