package com.nicos.material3expressivelist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicos.material3expressivelist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(title: Int, backButton: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        expandedHeight = 30.dp,
        title = {
            Text(
                stringResource(title),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp
                )
            )
        },
        navigationIcon = {
            if (backButton != null)
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .clickable { backButton() }
                        .fillMaxHeight()
                        .width(width = 50.dp)
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.DarkGray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(title: String, backButton: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp
                )
            )
        },
        navigationIcon = {
            if (backButton != null)
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .clickable { backButton() }
                        .fillMaxHeight()
                        .width(width = 50.dp)
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.DarkGray
        )
    )
}