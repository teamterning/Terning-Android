package com.terning.feature.onboarding.splash.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.home.home.navigation.navigateHome
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
    paddingValues : PaddingValues,
    navHostController: NavHostController,
) {
    composable<Splash>(
        popExitTransition = {
            ExitTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        enterTransition = {
            EnterTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        }
    ) {
        SplashRoute(
            paddingValues = paddingValues,
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