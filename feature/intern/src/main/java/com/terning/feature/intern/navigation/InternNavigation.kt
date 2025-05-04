package com.terning.feature.intern.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.terning.core.designsystem.util.DeeplinkDefaults
import com.terning.core.navigation.Route
import com.terning.feature.intern.InternRoute
import kotlinx.serialization.Serializable

fun NavController.navigateIntern(
    announcementId: Long = 0,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = Intern(announcementId),
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.internNavGraph(
    navHostController: NavHostController,
) {
    composable<Intern>(
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        enterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        },
        deepLinks = listOf(
            navDeepLink<Intern>(
                basePath = DeeplinkDefaults.build("intern")
            )
        )
    ) {
        val args = it.toRoute<Intern>()
        InternRoute(
            announcementId = args.announcementId,
            navController = navHostController
        )
    }
}

@Serializable
data class Intern(
    val announcementId: Long,
) : Route