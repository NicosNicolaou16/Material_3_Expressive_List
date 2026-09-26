@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.nicos.material3expressivelist.presentation.slider_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberRangeSliderState
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nicos.material3expressivelist.BuildConfig
import com.nicos.material3expressivelist.R
import com.nicos.material3expressivelist.presentation.CustomToolbar
import com.nicos.material3expressivelist.presentation.navigation.navigation_3.Navigator

@Composable
fun SliderRoot(
    navigator: Navigator
) {
    Scaffold(
        topBar = {
            CustomToolbar(
                title = stringResource(id = R.string.sliders),
                backButton = {
                    navigator.goBack()
                }
            )
        }
    ) { paddingValues ->
        SliderScreen(paddingValues = paddingValues)
    }
}

@Composable
private fun SliderScreen(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.padding(
            top = paddingValues.calculateTopPadding(),
            start = 25.dp,
            end = 25.dp,
        )
    ) {
        StandardSliderExample()
        Spacer(Modifier.height(40.dp))
        RangeSliderExample()
        Spacer(Modifier.height(40.dp))
        CenteredSliderExample()
    }
}

@Composable
private fun StandardSliderExample() {
    val sliderState = rememberSliderState(
        value = 0f,
        steps = 9,
        trackRange = 0f..100f,
    )

    Text(stringResource(R.string.standard_slider))
    Slider(
        state = sliderState,
        onValueChange = {
            sliderState.value = it
            if (BuildConfig.DEBUG) {
                Log.d("StandardSlider", "Slider value at: ${it.toInt()}")
            }
        },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        onValueChangeFinished = {
            if (BuildConfig.DEBUG) {
                Log.d("StandardSlider", "Slider value finished at: ${sliderState.value.toInt()}")
            }
        },
        track = { state ->
            SliderDefaults.Track(
                sliderState = state,
                trackInsideCornerSize = 5.dp,
                thumbTrackGapSize = 3.dp,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        },
        thumb = {
            CustomThumb(value = sliderState.value.toInt())
        }
    )
}

@Composable
private fun RangeSliderExample() {
    val rangeSliderState = rememberRangeSliderState(
        startValue = 0f,
        endValue = 100f,
        steps = 9,
        trackRange = 0f..100f,
    )

    LaunchedEffect(rangeSliderState.startValue, rangeSliderState.endValue) {
        if (BuildConfig.DEBUG) {
            Log.d(
                "RangeSlider",
                "Slider values at: ${rangeSliderState.startValue.toInt()} - ${rangeSliderState.endValue.toInt()}"
            )
        }
    }

    val startThumbAndTrackColors =
        SliderDefaults.colors(thumbColor = Color.Blue, activeTrackColor = Color.Red)
    Text(stringResource(R.string.range_slider))
    RangeSlider(
        state = rangeSliderState,
        onValueChange = { range ->
            rangeSliderState.startValue = range.start
            rangeSliderState.endValue = range.endInclusive
        },
        startThumb = {
            CustomThumb(
                value = rangeSliderState.startValue.toInt()
            )
        },
        endThumb = {
            CustomThumb(
                value = rangeSliderState.endValue.toInt()
            )
        },
        track = { state ->
            SliderDefaults.Track(
                colors = startThumbAndTrackColors,
                rangeSliderState = state,
            )
        },
    )
}

@Composable
private fun CenteredSliderExample() {
    val sliderState = rememberSliderState(
        value = 0f,
        steps = 9,
        trackRange = -100f..100f,
    )

    Text(text = stringResource(R.string.centered_slider))
    Slider(
        state = sliderState,
        onValueChange = {
            sliderState.value = it
            if (BuildConfig.DEBUG) {
                Log.d("CenteredSlider", "Slider value at: ${it.toInt()}")
            }
        },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        onValueChangeFinished = {
            if (BuildConfig.DEBUG) {
                Log.d("CenteredSlider", "Slider value finished at: ${sliderState.value.toInt()}")
            }
        },
        track = { state ->
            SliderDefaults.CenteredTrack(
                sliderState = state,
                trackInsideCornerSize = 5.dp,
                thumbTrackGapSize = 3.dp,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        },
        thumb = {
            CustomThumb(
                value = sliderState.value.toInt()
            )
        }
    )
}

@Composable
private fun CustomThumb(
    value: Int,
    color: Color = MaterialTheme.colorScheme.secondary
) {
    Box(
        Modifier
            .size(size = 30.dp)
            .shadow(
                elevation = 10.dp,
                shape = CircleShape,
            )
            .background(
                color = color,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}