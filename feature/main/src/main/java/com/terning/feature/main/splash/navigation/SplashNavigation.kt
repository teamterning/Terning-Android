package com.terning.feature.main.splash.navigation

import android.content.Intent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.terning.core.navigation.Route
import com.terning.feature.main.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashNavGraph(
    navigateHome: () -> Unit,
    navigateSignIn: () -> Unit,
) {
    composable<Splash>(
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "terning://splash"
                action = Intent.ACTION_VIEW
                mimeType = "*/*"
            }
        )
    ) {
        SplashRoute(
            navigateToHome = navigateHome,
            navigateToSignIn = navigateSignIn,
        )
    }
}

@Serializable
data object Splash : Route