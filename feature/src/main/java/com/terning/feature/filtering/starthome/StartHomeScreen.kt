package com.terning.feature.filtering.starthome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.home.home.navigation.navigateHome
import com.terning.feature.onboarding.signin.navigation.SignIn
import kotlinx.coroutines.delay

@Composable
fun StartHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    var isVisible by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenHeight = 780f / configuration.screenHeightDp

    LaunchedEffect(key1 = true) {
        delay(1000)
        isVisible = true
    }

    Box(
        modifier = modifier.fillMaxSize()
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
                visible = isVisible,
                enter = fadeIn(initialAlpha = 0.3f),
            ) {
                RectangleButton(
                    style = TerningTheme.typography.button0,
                    paddingVertical = 20.dp,
                    text = R.string.start_home_next_button,
                    onButtonClick = {
                        navController.navigateHome(
                            navOptions = NavOptions.Builder().setPopUpTo(
                                route = SignIn,
                                inclusive = true
                            ).build()
                        )
                    },
                )
            }
        }
    }
}

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
