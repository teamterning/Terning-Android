package com.terning.feature.onboarding.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.home.navigation.navigateHome
import com.terning.feature.onboarding.signin.navigation.navigateSignIn
import com.terning.feature.onboarding.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSplash(navOptions: NavOptions? = null) {
    navigate(
        route = Splash,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.splashNavGraph(
    navHostController: NavHostController,
) {
    composable<Splash> {
        SplashRoute(
            navigateToHome = {
                navHostController.navigateHome(
                    navOptions = NavOptions.Builder().setPopUpTo(
                        route = Splash,
                        inclusive = true
                    ).build()
                )
            },
            navigateToSignIn = {
                navHostController.navigateSignIn(
                    navOptions = NavOptions.Builder().setPopUpTo(
                        route = Splash,
                        inclusive = true
                    ).build()
                )
            }
        )
    }
}

@Serializable
data object Splash : Route