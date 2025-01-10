package com.terning.feature.search.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
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
    paddingValues: PaddingValues,
    navigateSearchProcess: () -> Unit,
    navigateIntern: (Long) -> Unit,
) {
    composable<Search> {
        SearchRoute(
            paddingValues = paddingValues,
            navigateToSearchProcess = navigateSearchProcess,
            navigateToIntern = navigateIntern
        )
    }
}

@Serializable
data object Search : MainTabRoute