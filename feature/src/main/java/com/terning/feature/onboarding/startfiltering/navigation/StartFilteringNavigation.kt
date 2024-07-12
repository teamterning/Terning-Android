package com.terning.feature.onboarding.startfiltering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.startfiltering.StartFilteringScreen
import kotlinx.serialization.Serializable

fun NavController.navigateStartFiltering(navOptions: NavOptions? = null) {
    navigate(
        route = StartFiltering,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.startFilteringNavGraph(
    navHostController: NavHostController
) {
    composable<StartFiltering> {
        StartFilteringScreen(navController = navHostController)
    }
}

@Serializable
data object StartFiltering : Route