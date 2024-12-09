package com.terning.feature.filtering.starthome

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieConstants
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.component.item.TerningLottieAnimation
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.filtering.R
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
        onClick = navigateToHome,
        buttonState = state.isButtonVisible,
        screenHeight = screenHeight,
    )
}

@Composable
fun StartHomeScreen(
    onClick: () -> Unit,
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
        Spacer(modifier = Modifier.height((128 * screenHeight).dp))
        Text(
            text = stringResource(id = R.string.start_home_title),
            style = TerningTheme.typography.title1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 52.dp)
        )
        TerningLottieAnimation(
            jsonFile = R.raw.terning_start_home,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .aspectRatio(1f),
            iterations = LottieConstants.IterateForever
        )
        Spacer(modifier = Modifier.height((88 * screenHeight).dp))
        AnimatedVisibility(
            visible = buttonState,
            enter = fadeIn(initialAlpha = 0.3f),
        ) {
            RoundButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 17.dp,
                text = R.string.start_home_next_button,
                onButtonClick = onClick,
                cornerRadius = 10.dp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height((49 * screenHeight).dp))
    }
}

private const val DELAY: Long = 1000

@Preview(showBackground = true)
@Composable
private fun StartHomeScreenPreview() {
    TerningPointTheme {
        StartHomeScreen(
            onClick = {},
            buttonState = true,
            screenHeight = 1f
        )
    }
}