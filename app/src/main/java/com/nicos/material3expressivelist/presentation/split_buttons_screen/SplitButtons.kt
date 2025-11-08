@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.nicos.material3expressivelist.presentation.split_buttons_screen

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.nicos.material3expressivelist.presentation.CustomToolbar
import com.nicos.material3expressivelist.R

@Composable
fun SplitButtonsRoot(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = stringResource(R.string.split_buttons),
                backButton = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        SplitButtons(
            paddingValues = paddingValues
        )
    }
}

@Composable
fun SplitButtons(
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    var checked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        SplitButtonLayout(
            leadingButton = {
                SplitButtonDefaults.LeadingButton(onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.leading_button), Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_24px),
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = stringResource(R.string.edit_button),
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(stringResource(R.string.my_button))
                }
            },
            trailingButton = {
                val description = stringResource(R.string.toggle_button)
                // Icon-only trailing button should have a tooltip for a11y.
                TooltipBox(
                    positionProvider =
                        TooltipDefaults.rememberTooltipPositionProvider(
                            TooltipAnchorPosition.Above
                        ),
                    tooltip = { PlainTooltip { Text(description) } },
                    state = rememberTooltipState(),
                ) {
                    SplitButtonDefaults.TrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        modifier =
                            Modifier.semantics {
                                stateDescription = if (checked) "Expanded" else "Collapsed"
                                contentDescription = description
                            },
                    ) {
                        val rotation: Float by
                        animateFloatAsState(
                            targetValue = if (checked) 180f else 0f,
                            label = stringResource(R.string.trailing_icon_rotation),
                        )
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            modifier =
                                Modifier
                                    .size(SplitButtonDefaults.TrailingIconSize)
                                    .graphicsLayer {
                                        this.rotationZ = rotation
                                    },
                            contentDescription = stringResource(R.string.trailing_icon),
                        )
                    }
                }
            },
        )

        DropdownMenu(expanded = checked, onDismissRequest = { checked = false }) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.edit)) },
                onClick = {
                    Toast.makeText(context, R.string.edit_button, Toast.LENGTH_SHORT).show()
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_24px),
                        contentDescription = null
                    )
                },
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.settings)) },
                onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.settings_button), Toast.LENGTH_SHORT
                    ).show()
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.settings_24px),
                        contentDescription = null
                    )
                },
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text(stringResource(R.string.send_feedback)) },
                onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.send_feedback_button), Toast.LENGTH_SHORT
                    ).show()
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.mail_24px),
                        contentDescription = null
                    )
                },
                trailingIcon = { Text(stringResource(R.string.f11), textAlign = TextAlign.Center) },
            )
        }
    }
}