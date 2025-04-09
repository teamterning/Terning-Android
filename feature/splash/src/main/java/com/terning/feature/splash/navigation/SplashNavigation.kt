package com.terning.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.splash.SplashRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashNavGraph(
    navigateHome: () -> Unit,
    navigateSignIn: () -> Unit,
) {
    composable<Splash>(
        deepLinks = listOf(
            navDeepLink<Splash>(
                basePath = "terning://splash"
            )
        )
    ) {
        val redirect = it.toRoute<Splash>().redirect
        SplashRoute(
            redirect = redirect,
            navigateToHome = navigateHome,
            navigateToSignIn = navigateSignIn,
        )
    }
}

@Serializable
data class Splash(
    @SerialName("redirect")
    val redirect: String?
) : Route