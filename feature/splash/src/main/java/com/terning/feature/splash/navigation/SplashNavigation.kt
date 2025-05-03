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
    navigateHome: () -> Unit,
    navigateSignIn: () -> Unit,
    navigateSearch: () -> Unit,
    navigateCalendar: () -> Unit,
    navigateToInternDetail: (internId: String) -> Unit,
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
            redirect = args.redirect,
            internId = args.internId,
            navigateToHome = navigateHome,
            navigateToSignIn = navigateSignIn,
            navigateToSearch = navigateSearch,
            navigateToCalendar = navigateCalendar,
            navigateToInternDetail = navigateToInternDetail,
        )
    }
}

@Serializable
data class Splash(
    @SerialName("redirect")
    val redirect: String? = null,
    @SerialName("internId")
    val internId: String? = null
) : Route