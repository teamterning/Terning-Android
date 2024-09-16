package com.terning.feature.filtering.starthome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.R
import kotlinx.coroutines.delay

@Composable
fun StartHomeRoute(
    navigateToHome: () -> Unit,
    viewModel: StartHomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current
    val screenHeight = 780f / configuration.screenHeightDp

    LaunchedEffect(key1 = true) {
        delay(DELAY)
        viewModel.updateButtonState()
    }

    StartHomeScreen(
        navigateToHome = navigateToHome,
        buttonState = state.isButtonVisible,
        screenHeight = screenHeight,
    )
}

@Composable
fun StartHomeScreen(
    navigateToHome: () -> Unit,
    buttonState: Boolean,
    screenHeight: Float,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height((128 * screenHeight).dp))
            Text(
                text = stringResource(id = R.string.start_home_title),
                style = TerningTheme.typography.title1,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 30.dp)
            )
            StartHomeLottieAnimation()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 12.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            AnimatedVisibility(
                visible = buttonState,
                enter = fadeIn(initialAlpha = 0.3f),
            ) {
                RectangleButton(
                    style = TerningTheme.typography.button0,
                    paddingVertical = 20.dp,
                    text = R.string.start_home_next_button,
                    onButtonClick = navigateToHome,
                )
            }
        }
    }
}

private const val DELAY: Long = 1000

@Composable
fun StartHomeLottieAnimation(
    modifier: Modifier = Modifier
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.Asset("terning_start_home.json"))
    LottieAnimation(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(
                (lottieComposition?.bounds
                    ?.width()
                    ?.toFloat()
                    ?: 1f) / (lottieComposition?.bounds?.height() ?: 1)
            )
            .padding(horizontal = 24.dp),
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever
    )
}

@Preview(showBackground = true)
@Composable
fun StartHomeScreenPreview() {
    TerningPointTheme {
        StartHomeScreen(
            navigateToHome = {},
            buttonState = true,
            screenHeight = 1f
        )
    }
}