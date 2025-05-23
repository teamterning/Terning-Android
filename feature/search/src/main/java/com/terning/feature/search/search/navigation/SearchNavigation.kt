package com.terning.feature.search.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.terning.core.designsystem.util.DeeplinkDefaults
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
    composable<Search>(
        deepLinks = listOf(
            navDeepLink<Search>(
                basePath = DeeplinkDefaults.build("search")
            )
        )
    ) {
        SearchRoute(
            paddingValues = paddingValues,
            navigateToSearchProcess = navigateSearchProcess,
            navigateToIntern = navigateIntern
        )
    }
}

@Serializable
data object Search : MainTabRoute