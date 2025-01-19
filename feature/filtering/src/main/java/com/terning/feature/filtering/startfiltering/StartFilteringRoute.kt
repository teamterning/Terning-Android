package com.terning.feature.filtering.startfiltering

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieConstants
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.component.item.TerningLottieAnimation
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.filtering.R
import kotlinx.coroutines.delay

@Composable
fun StartFilteringRoute(
    onStartClick: () -> Unit,
    onLaterClick: () -> Unit,
    viewModel: StartFilteringViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current
    val screenHeight = 780f / configuration.screenHeightDp

    val amplitudeTracker = LocalTracker.current

    LaunchedEffect(key1 = true) {
        delay(DELAY)
        viewModel.updateButtonState()
    }

    StartFilteringScreen(
        onStartClick = {
            onStartClick()
            amplitudeTracker.track(
                type = EventType.CLICK,
                name = "start_service"
            )
        },
        onLaterClick = {
            onLaterClick()
            amplitudeTracker.track(
                type = EventType.CLICK,
                name = "skip_plan"
            )
        },
        buttonState = state.isButtonVisible,
        screenHeight = screenHeight,
    )
}

@Composable
fun StartFilteringScreen(
    onStartClick: () -> Unit,
    onLaterClick: () -> Unit,
    buttonState: Boolean,
    screenHeight: Float,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height((138 * screenHeight).dp))
        Text(
            text = stringResource(id = R.string.start_filtering_title),
            style = TerningTheme.typography.title1,
            textAlign = TextAlign.Center
        )
        TerningLottieAnimation(
            jsonFile = R.raw.terning_onboarding_start,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height((79 * screenHeight).dp))
        ButtonAnimation(
            buttonState = buttonState,
            onStartClick = onStartClick,
            onLaterClick = onLaterClick
        )
        Spacer(modifier = Modifier.height((16 * screenHeight).dp))
    }
}

@Composable
private fun ButtonAnimation(
    buttonState: Boolean,
    onStartClick: () -> Unit,
    onLaterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = buttonState,
        enter = fadeIn(initialAlpha = 0.3f),
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 17.dp,
                text = R.string.start_filtering_button,
                onButtonClick = onStartClick,
                cornerRadius = 10.dp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.start_filtering_later),
                style = TerningTheme.typography.detail3.copy(
                    textDecoration = TextDecoration.Underline
                ),
                color = Grey500,
                modifier = Modifier.noRippleClickable { onLaterClick() }
            )
        }
    }
}

private const val DELAY: Long = 1000

@Preview(showBackground = true)
@Composable
private fun StartFilteringScreenPreview() {
    TerningPointTheme {
        StartFilteringScreen(
            onStartClick = {},
            onLaterClick = {},
            buttonState = true,
            screenHeight = 1f
        )
    }
}