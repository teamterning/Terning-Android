package com.terning.feature.filtering.filtering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.filtering.filteringone.FilteringOneRoute
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringOne(
    name: String,
    navOptions: NavOptions? = null
) {
    navigate(
        route = FilteringOne(name = name),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringOneNavGraph(
    navHostController: NavHostController
) {
    composable<FilteringOne> {
        val args = it.toRoute<FilteringOne>()
        FilteringOneRoute(
            name = args.name,
            onNextClick = navHostController::navigateFilteringTwo,
            navigateUp = navHostController::navigateUp
        )
    }
}

@Serializable
data class FilteringOne(
    val name: String
) : Route