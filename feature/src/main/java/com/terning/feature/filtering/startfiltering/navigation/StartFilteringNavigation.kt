package com.terning.feature.filtering.startfiltering.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.filtering.filtering.navigation.navigateFilteringOne
import com.terning.feature.filtering.startfiltering.StartFilteringScreen
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
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    composable<StartFiltering> {
        val args = it.toRoute<StartFiltering>()
        StartFilteringScreen(
            paddingValues = paddingValues,
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