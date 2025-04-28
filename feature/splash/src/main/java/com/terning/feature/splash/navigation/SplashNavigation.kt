package com.terning.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.splash.SplashRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SPLASH_PATH: String = "terning://splash"

fun NavGraphBuilder.splashNavGraph(
    navigateHome: () -> Unit,
    navigateSignIn: () -> Unit,
    navigateSearch: () -> Unit,
    navigateCalendar: () -> Unit
) {
    composable<Splash>(
        deepLinks = listOf(
            navDeepLink<Splash>(
                basePath = SPLASH_PATH
            )
        )
    ) {
        val args = it.toRoute<Splash>()
        SplashRoute(
            redirect = args.redirect,
            navigateToHome = navigateHome,
            navigateToSignIn = navigateSignIn,
            navigateToSearch = navigateSearch,
            navigateToCalendar = navigateCalendar
        )
    }
}

@Serializable
data class Splash(
    @SerialName("redirect")
    val redirect: String?
) : Route