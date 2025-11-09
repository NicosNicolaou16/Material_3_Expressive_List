@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.nick.samplecomposeandhilt.compose.main_bottom_navigation_view.processes.processes_screen.screens.floating_toolbar

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nicos.material3expressivelist.R
import com.nicos.material3expressivelist.presentation.CustomToolbar

@Composable
fun FloatingToolbarRoot(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = stringResource(id = R.string.floating_toolbar),
                backButton = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        FloatingToolbar(paddingValues = paddingValues)
    }
}

@Composable
fun FloatingToolbar(
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    var expanded by rememberSaveable { mutableStateOf(true) }
    val vibrantColors = FloatingToolbarDefaults.vibrantFloatingToolbarColors(
        toolbarContainerColor = MaterialTheme.colorScheme.primary,
        toolbarContentColor = MaterialTheme.colorScheme.onPrimary,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(paddingValues = paddingValues)
    ) {
        HorizontalFloatingToolbar(
            modifier = Modifier.align(Alignment.BottomCenter),
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
                        contentDescription = stringResource(id = R.string.settings),
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = {
                    Toast.makeText(context, context.getString(R.string.edit), Toast.LENGTH_SHORT)
                        .show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_24px),
                        contentDescription = stringResource(id = R.string.edit)
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
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(enabled = true, onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.processes),
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_processes),
                        contentDescription = stringResource(id = R.string.processes)
                    )
                }
            },
        )
    }
}