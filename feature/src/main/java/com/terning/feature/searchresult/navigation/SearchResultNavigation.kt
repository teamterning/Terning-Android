package com.terning.feature.searchresult.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.searchresult.SearchResultRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSearchResult(navOptions: NavOptions? = null) {
    navigate(
        route = SearchResult,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.searchResultNavGraph(
    navHostController: NavHostController,
) {
    composable<SearchResult> {
        SearchResultRoute(
            navController = navHostController
        )
    }
}

@Serializable
data object SearchResult : MainTabRoute