package com.terning.feature.filtering.filtering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.filtering.filtering.FilteringThreeScreen
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringThree(navOptions: NavOptions? = null) {
    navigate(
        route = FilteringThree,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringThreeNavGraph(
    navHostController: NavHostController
) {
    composable<FilteringThree> {
        FilteringThreeScreen(
            navController = navHostController
        )
    }
}

@Serializable
data object FilteringThree : Route