package com.terning.feature.search.searchprocess.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.search.searchprocess.SearchProcessRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSearchProcess(navOptions: NavOptions? = null) {
    navigate(
        route = SearchProcess,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.searchProcessNavGraph(
    navHostController: NavHostController,
) {
    composable<SearchProcess> {
        SearchProcessRoute(
            navController = navHostController
        )
    }
}

@Serializable
data object SearchProcess : Route