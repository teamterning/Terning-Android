package com.terning.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.terning.core.designsystem.util.DeeplinkDefaults
import com.terning.core.navigation.Route
import com.terning.feature.splash.SplashRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashNavGraph(
    navigateHome: (internId: String?) -> Unit,
    navigateSignIn: () -> Unit,
    navigateSearch: () -> Unit,
    navigateCalendar: () -> Unit,
) {
    composable<Splash>(
        deepLinks = listOf(
            navDeepLink<Splash>(
                basePath = DeeplinkDefaults.build("splash")
            )
        )
    ) {
        val args = it.toRoute<Splash>()
        SplashRoute(
            action = args.action,
            id = args.id,
            navigateToHome = navigateHome,
            navigateToSignIn = navigateSignIn,
            navigateToSearch = navigateSearch,
            navigateToCalendar = navigateCalendar
        )
    }
}

@Serializable
data class Splash(
    @SerialName("action")
    val action: String? = null,
    @SerialName("id")
    val id: String? = null
) : Route
