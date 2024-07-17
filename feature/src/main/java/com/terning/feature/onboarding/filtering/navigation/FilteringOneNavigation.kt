package com.terning.feature.onboarding.filtering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.filtering.FilteringOneScreen
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringOne(navOptions: NavOptions? = null) {
    navigate(
        route = FilteringOne,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringOneNavGraph(
    navHostController: NavHostController
) {
    composable<FilteringOne> {
        FilteringOneScreen(
            navController = navHostController
        )
    }
}

@Serializable
data object FilteringOne : Route