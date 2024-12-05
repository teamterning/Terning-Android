package com.terning.feature.filtering.starthome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.filtering.starthome.StartHomeRoute
import kotlinx.serialization.Serializable

fun NavController.navigateStartHome(navOptions: NavOptions? = null) {
    navigate(
        route = StartHome,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.startHomeNavGraph(
    navigateHome: () -> Unit
) {
    composable<StartHome> {
        StartHomeRoute(
            navigateToHome = navigateHome
        )
    }
}

@Serializable
data object StartHome : Route