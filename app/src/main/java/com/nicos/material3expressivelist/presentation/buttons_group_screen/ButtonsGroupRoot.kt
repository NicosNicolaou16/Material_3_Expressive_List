@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.nicos.material3expressivelist.presentation.buttons_group_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nicos.material3expressivelist.R
import com.nicos.material3expressivelist.presentation.CustomToolbar
import com.nicos.material3expressivelist.ui.theme.Material3ExpressiveListTheme
import kotlin.collections.fill
import kotlin.collections.forEachIndexed
import kotlin.collections.lastIndex

@Composable
fun ButtonGroupsRoot(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Button Groups",
                backButton = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Title(stringResource(R.string.multiple_selection))
            ButtonsGroup()
            Title(stringResource(R.string.single_selection))
            ButtonsGroup(isSingleSelection = true)
        }
    }
}

@Composable
fun Title(text: String) {
    Text(
        text, textAlign = TextAlign.Center, style = TextStyle(
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
        )
    )
}

@Composable
fun ButtonsGroup(
    isSingleSelection: Boolean = false,
) {
    val options = listOf(
        stringResource(R.string.work),
        stringResource(R.string.restaurant), stringResource(R.string.coffee)
    )
    val unCheckedIcons: List<Int> =
        listOf(
            R.drawable.work_24px,
            R.drawable.restaurant_24px,
            R.drawable.coffee_24px,
        )
    val checkedIcons: List<Int> = listOf(
        R.drawable.work_filled_24px,
        R.drawable.restaurant_filled_24px,
        R.drawable.coffee_filled_24px,
    )
    val checked = remember {
        mutableStateListOf(
            false,
            false,
            true
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
    ) {
        val modifiers = listOf(Modifier.weight(1f), Modifier.weight(1.5f), Modifier.weight(1f))
        options.forEachIndexed { index, label ->
            ToggleButton(
                checked = checked[index],
                onCheckedChange = {
                    if (isSingleSelection) checked.fill(false)
                    checked[index] = it
                },
                modifier = modifiers[index],
                colors = ToggleButtonDefaults.toggleButtonColors(
                    checkedContainerColor = MaterialTheme.colorScheme.primary,
                    checkedContentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                shapes =
                    when (index) {
                        0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    },
            ) {
                ContainerButton(
                    checked = checked,
                    index = index,
                    label = label,
                    unCheckedIcons = unCheckedIcons,
                    checkedIcons = checkedIcons,
                )
            }
        }
    }
}

@Composable
fun ContainerButton(
    checked: List<Boolean>,
    index: Int,
    label: String,
    unCheckedIcons: List<Int>,
    checkedIcons: List<Int>,
) {
    Icon(
        painter = painterResource(id = if (checked[index]) checkedIcons[index] else unCheckedIcons[index]),
        contentDescription = checkedIcons[index].toString(),
    )
    Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
    Text(label)
}

@Preview(showBackground = true, name = "Multiple Selection Group")
@Composable
private fun ButtonsGroupMultiSelectPreview() {
    Material3ExpressiveListTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Title(text = "Multiple Selection")
            ButtonsGroup(isSingleSelection = false)
        }
    }
}

@Preview(showBackground = true, name = "Single Selection Group")
@Composable
private fun ButtonsGroupSingleSelectPreview() {
    Material3ExpressiveListTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Title(text = "Single Selection")
            ButtonsGroup(isSingleSelection = true)
        }
    }
}