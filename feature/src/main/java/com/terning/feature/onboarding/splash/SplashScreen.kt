package com.terning.feature.onboarding.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.TerningMain
import com.terning.feature.R
import com.terning.feature.home.home.navigation.navigateHome
import com.terning.feature.onboarding.signin.navigation.navigateSignIn
import com.terning.feature.onboarding.splash.navigation.Splash

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
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
                    is SplashState.GetHasAccessToken -> {
                        if (sideEffect.hasAccessToken) navController.navigateHome(
                            navOptions = NavOptions.Builder().setPopUpTo(
                                 route = Splash,
                                inclusive = true
                            ).build()
                        )
                        else navController.navigateSignIn()
                    }
                }
            }

    }

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
