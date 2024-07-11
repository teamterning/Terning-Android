package com.terning.feature.onboarding.start.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.start.StartScreen
import kotlinx.serialization.Serializable

fun NavController.navigateStart(navOptions: NavOptions? = null) {
    navigate(
        route = Start,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.startNavGraph(
    navHostController: NavHostController
) {
    composable<Start> {
        StartScreen(navController = navHostController)
    }
}

@Serializable
data object Start : Route