package com.terning.feature.filtering.starthome.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.filtering.starthome.StartHomeScreen
import kotlinx.serialization.Serializable

fun NavController.navigateStartHome(navOptions: NavOptions? = null) {
    navigate(
        route = StartHome,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.startHomeNavGraph(
    navHostController: NavHostController
) {
    composable<StartHome>(
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
        StartHomeScreen(navController = navHostController)
    }
}

@Serializable
data object StartHome : Route