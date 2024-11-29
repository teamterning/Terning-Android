package com.terning.feature.search.search.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.intern.navigation.navigateIntern
import com.terning.feature.search.search.SearchRoute
import com.terning.feature.search.searchprocess.navigation.navigateSearchProcess
import kotlinx.serialization.Serializable

fun NavController.navigateSearch(navOptions: NavOptions? = null) {
    navigate(
        route = Search,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.searchNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    composable<Search>(
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        enterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        }
    ) {
        SearchRoute(
            modifier = Modifier.padding(paddingValues),
            navigateToSearchProcess = { navHostController.navigateSearchProcess() },
            navigateToIntern = { announcementId -> navHostController.navigateIntern(announcementId) }
        )
    }
}

@Serializable
data object Search : MainTabRoute