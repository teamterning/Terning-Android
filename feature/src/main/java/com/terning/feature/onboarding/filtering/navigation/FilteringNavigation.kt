package com.terning.feature.onboarding.filtering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.filtering.FilteringOneScreen
import kotlinx.serialization.Serializable

fun NavController.navigateFiltering(navOptions: NavOptions? = null) {
    navigate(
        route = Filtering,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringNavGraph(
    navHostController: NavHostController
) {
    composable<Filtering> {
        FilteringOneScreen(
            navController = navHostController
        )
    }
}

@Serializable
data object Filtering : Route