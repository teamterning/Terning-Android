package com.terning.feature.home.changefilter.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.home.changefilter.ChangeFilterRoute
import kotlinx.serialization.Serializable

fun NavController.navigateChangeFilter(navOptions: NavOptions? = null) {
    navigate(
        route = ChangeFilter,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.changeFilterNavGraph(
    navHostController: NavHostController
) {
    composable<ChangeFilter>(
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
        ChangeFilterRoute(
            navController = navHostController
        )
    }
}

@Serializable
data object ChangeFilter : Route