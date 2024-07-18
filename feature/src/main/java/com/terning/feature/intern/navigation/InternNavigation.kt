package com.terning.feature.intern.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.intern.InternRoute
import kotlinx.serialization.Serializable

fun NavController.navigateIntern(
    navOptions: NavOptions? = null,
    announcementId: Long = 0,
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
        }
    ) {
        val args = it.toRoute<Intern>()
        InternRoute(
            navController = navHostController,
            announcementId = args.announcementId,
        )
    }
}

@Serializable
data class Intern(
    val announcementId: Long,
) : Route