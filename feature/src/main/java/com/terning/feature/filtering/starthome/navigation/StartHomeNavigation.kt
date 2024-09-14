package com.terning.feature.filtering.starthome.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.terning.core.navigation.Route
import com.terning.feature.filtering.starthome.StartHomeRoute
import com.terning.feature.home.home.navigation.navigateHome
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
    composable<StartHome> {
        val navOptions = navOptions {
            popUpTo(id = navHostController.graph.id) {
                inclusive = true
            }
        }
        StartHomeRoute(
            navigateToHome = {
                navHostController.navigateHome(
                    navOptions = navOptions
                )
            }
        )
    }
}

@Serializable
data object StartHome : Route