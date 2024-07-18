package com.terning.feature.onboarding.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.signup.SignUpRoute
import com.terning.feature.onboarding.splash.SplashScreen
import kotlinx.serialization.Serializable

fun NavController.navigateSplash(navOptions: NavOptions? = null) {
    navigate(
        route = Splash,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.splashNavGraph(
    navHostController: NavHostController
) {
    composable<Splash> {
        SplashScreen(
            navController = navHostController,
        )
    }
}

@Serializable
data object Splash: Route