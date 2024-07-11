package com.terning.feature.search.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.search.search.SearchRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSearch(navOptions: NavOptions? = null) {
    navigate(
        route = Search,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.searchNavGraph(
    navHostController: NavHostController,
) {
    composable<Search> {
        SearchRoute(
            navController = navHostController
        )
    }
}

@Serializable
data object Search : MainTabRoute