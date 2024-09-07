package com.terning.feature.filtering.startfiltering

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieConstants
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.item.TerningLottieAnimation
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.R
import kotlinx.coroutines.delay

@Composable
fun StartFilteringRoute(
    paddingValues: PaddingValues = PaddingValues(),
    onNextClick: () -> Unit,
    viewModel: StartFilteringViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current
    val screenHeight = 780f / configuration.screenHeightDp

    LaunchedEffect(key1 = true) {
        delay(DELAY)
        viewModel.updateButtonState()
    }

    StartFilteringScreen(
        onNextClick = onNextClick,
        buttonState = state.isButtonVisible,
        screenHeight = screenHeight,
        paddingValues = paddingValues
    )

}

@Composable
fun StartFilteringScreen(
    onNextClick: () -> Unit,
    buttonState: Boolean,
    screenHeight: Float,
    paddingValues: PaddingValues = PaddingValues()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height((128 * screenHeight).dp))
            Text(
                text = stringResource(id = R.string.start_filtering_title),
                style = TerningTheme.typography.title1,
                modifier = Modifier.padding(bottom = 36.dp),
                textAlign = TextAlign.Center
            )

            TerningLottieAnimation(
                jsonString = "terning_onboarding_start.json",
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(372.dp)
                    .padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.weight(2f))
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        ) {
            AnimatedVisibility(
                visible = buttonState,
                enter = fadeIn(initialAlpha = 0.3f),
            ) {
                RectangleButton(
                    style = TerningTheme.typography.button0,
                    paddingVertical = 20.dp,
                    text = R.string.start_filtering_button,
                    onButtonClick = onNextClick,
                )
            }
        }
    }
}

private const val DELAY: Long = 1000

@Preview(showBackground = true)
@Composable
fun StartFilteringScreenPreview() {
    TerningPointTheme {
        StartFilteringScreen(
            onNextClick = {},
            buttonState = true,
            screenHeight = 1f
        )
    }
}