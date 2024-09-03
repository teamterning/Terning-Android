package com.terning.feature.filtering.startfiltering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.filtering.filtering.navigation.navigateFilteringOne
import com.terning.feature.filtering.startfiltering.StartFilteringRoute
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
        StartFilteringRoute(
            onNextClick = {
                navHostController.navigateFilteringOne(args.name)
            }
        )
    }
}

@Serializable
data class StartFiltering(
    val name: String
) : Route