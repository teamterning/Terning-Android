package com.terning.feature.onboarding.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.feature.R

@Composable
fun SplashRoute(
    paddingValues: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
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

    SplashScreen(paddingValues = paddingValues)
}

@Composable
fun SplashScreen(
    paddingValues: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(TerningMain),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "splash screen"
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
