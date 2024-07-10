package com.terning.feature.searchprocess.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.searchprocess.SearchProcessRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSearchProcess(navOptions: NavOptions? = null) {
    navigate(
        route = SearchProcess,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.searchProcessNavGraph() {
    composable<SearchProcess> {
        SearchProcessRoute()
    }
}

@Serializable
data object SearchProcess : MainTabRoute