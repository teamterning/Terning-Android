package com.terning.feature.onboarding.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.feature.R

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = TerningMain
        )
        systemUiController.setNavigationBarColor(
            color = TerningMain
        )
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = true) {
        viewModel.showSplash(lifecycleOwner)
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SplashState.HasAccessToken -> {
                        if (sideEffect.hasAccessToken) navigateToHome()
                        else navigateToSignIn()
                    }
                }
            }
    }

    SplashScreen()
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TerningMain),
    ) {
        TerningImage(
            painter = R.drawable.ic_splash,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TerningPointTheme {
        SplashScreen()
    }
}
