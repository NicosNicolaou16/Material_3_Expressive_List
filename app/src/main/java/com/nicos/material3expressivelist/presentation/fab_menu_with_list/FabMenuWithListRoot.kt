@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.nicos.material3expressivelist.presentation.fab_menu_with_list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nicos.material3expressivelist.R
import com.nicos.material3expressivelist.presentation.CustomToolbar
import kotlin.collections.forEachIndexed
import kotlin.ranges.until
import kotlin.to

@Composable
fun FabMenuWithListRoot(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = stringResource(R.string.fab_menu_with_list),
                backButton = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        FabMenuScreen(
            paddingValues = paddingValues
        )
    }
}

@SuppressLint("RememberInComposition")
@Composable
fun FabMenuScreen(
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val fabVisible by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    val focusRequester = FocusRequester()
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

        BackHandler(fabMenuExpanded) { fabMenuExpanded = false }

        FloatingActionButtonMenu(
            modifier = Modifier.align(Alignment.BottomEnd),
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
                            }
                            .animateFloatingActionButton(
                                visible = fabVisible || fabMenuExpanded,
                                alignment = Alignment.BottomEnd,
                            )
                            .focusRequester(focusRequester),
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
                                                    fabMenuExpanded = false
                                                    true
                                                },
                                            )
                                        )
                                }
                            }
                            .then(
                                if (i == 0) {
                                    Modifier.onKeyEvent {
                                        // Navigating back from the first item should go back to the
                                        // FAB menu button.
                                        if (
                                            it.type == KeyEventType.KeyDown &&
                                            (it.key == Key.DirectionUp ||
                                                    (it.isShiftPressed && it.key == Key.Tab))
                                        ) {
                                            focusRequester.requestFocus()
                                            return@onKeyEvent true
                                        }
                                        return@onKeyEvent false
                                    }
                                } else {
                                    Modifier
                                }
                            ),
                    onClick = {
                        fabMenuExpanded = false
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
}