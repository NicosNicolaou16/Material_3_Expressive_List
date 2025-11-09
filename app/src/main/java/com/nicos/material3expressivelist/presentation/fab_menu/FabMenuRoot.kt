@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.nicos.material3expressivelist.presentation.fab_menu

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Snooze
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import androidx.navigation.NavHostController
import com.nicos.material3expressivelist.R
import com.nicos.material3expressivelist.presentation.CustomToolbar
import kotlin.collections.forEachIndexed
import kotlin.to

@Composable
fun FabMenuRoot(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = stringResource(R.string.fab_menu),
                backButton = {
                    navController.popBackStack()
                }
            )
        },
        floatingActionButton = {
            FabMenu(
                paddingValues = PaddingValues()
            )
        }
    ) { paddingValues ->
    }
}

@SuppressLint("RememberInComposition")
@Composable
fun FabMenu(
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val items =
        listOf(
            Icons.AutoMirrored.Filled.Message to stringResource(R.string.reply),
            Icons.Filled.People to stringResource(R.string.reply_all),
            Icons.Filled.Contacts to stringResource(R.string.forward),
            Icons.Filled.Snooze to stringResource(R.string.snooze),
            Icons.Filled.Archive to stringResource(R.string.archive),
            Icons.AutoMirrored.Filled.Label to stringResource(R.string.label),
        )
    var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

    BackHandler(fabMenuExpanded) { fabMenuExpanded = false }

    FloatingActionButtonMenu(
        expanded = fabMenuExpanded,
        button = {
            ToggleFloatingActionButton(
                modifier =
                    Modifier
                        .semantics {
                            traversalIndex = -1f
                            stateDescription =
                                if (fabMenuExpanded) context.getString(R.string.expanded) else context.getString(
                                    R.string.collapsed
                                )
                            contentDescription = context.getString(R.string.toggle_menu)
                        },
                checked = fabMenuExpanded,
                onCheckedChange = { fabMenuExpanded = !fabMenuExpanded },
            ) {
                val imageVector by remember {
                    derivedStateOf {
                        if (checkedProgress > 0.5f) Icons.Filled.Close else Icons.Filled.Add
                    }
                }
                Icon(
                    painter = rememberVectorPainter(imageVector),
                    contentDescription = null,
                    modifier = Modifier.animateIcon({ checkedProgress }),
                )
            }
        },
    ) {
        items.forEachIndexed { i, item ->
            FloatingActionButtonMenuItem(
                modifier =
                    Modifier
                        .semantics {
                            isTraversalGroup = true
                            if (i == items.size - 1) {
                                customActions =
                                    listOf(
                                        CustomAccessibilityAction(
                                            label = context.getString(R.string.close_menu),
                                            action = {
                                                true
                                            },
                                        )
                                    )
                            }
                        },
                onClick = {
                    Toast.makeText(
                        context,
                        "onClick: ${item.second}",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                icon = { Icon(item.first, contentDescription = null) },
                text = { Text(text = item.second) },
            )
        }
    }
}