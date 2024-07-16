package com.terning.feature.onboarding.startfiltering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.startfiltering.StartFilteringScreen
import kotlinx.serialization.Serializable

fun NavController.navigateStartFiltering(
    name: String,
    navOptions: NavOptions? = null
) {
    navigate(
        route = StartFiltering(name = name),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.startFilteringNavGraph(
    navHostController: NavHostController
) {
    composable<StartFiltering> {
        val args = it.toRoute<StartFiltering>()
        StartFilteringScreen(
            name = args.name,
            navController = navHostController,
        )
    }
}

@Serializable
data class StartFiltering(
    val name: String
) : Route