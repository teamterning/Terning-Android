package com.terning.feature.onboarding.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSplash(navOptions: NavOptions? = null) {
    navigate(
        route = Splash,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.splashNavGraph(
    navigateHome: () -> Unit,
    navigateSignIn: () -> Unit,
) {
    composable<Splash> {
        SplashRoute(
            navigateToHome = navigateHome,
            navigateToSignIn = navigateSignIn,
        )
    }
}

@Serializable
data object Splash : Route