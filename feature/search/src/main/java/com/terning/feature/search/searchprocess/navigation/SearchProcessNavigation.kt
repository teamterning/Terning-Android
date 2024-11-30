package com.terning.feature.search.searchprocess.navigation

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
    navigateIntern: (Long) -> Unit,
    paddingValues: PaddingValues
) {
    composable<SearchProcess>(
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
        SearchProcessRoute(
            modifier = Modifier.padding(paddingValues),
            navController = navHostController,
            navigateIntern = { internshipAnnouncementId ->
                navigateIntern(internshipAnnouncementId)
            }
        )
    }
}

@Serializable
data object SearchProcess : Route