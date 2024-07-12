package com.terning.feature.onboarding.filteringstart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.filteringstart.FilteringStartScreen
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringStart(navOptions: NavOptions? = null) {
    navigate(
        route = FilteringStart,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringStartNavGraph(
    navHostController: NavHostController
) {
    composable<FilteringStart> {
        FilteringStartScreen(navController = navHostController)
    }
}

@Serializable
data object FilteringStart : Route