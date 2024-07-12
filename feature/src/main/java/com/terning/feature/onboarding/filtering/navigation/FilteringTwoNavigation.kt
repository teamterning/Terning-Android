package com.terning.feature.onboarding.filtering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.filtering.FilteringThreeScreen
import com.terning.feature.onboarding.filtering.FilteringTwoScreen
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringThree(navOptions: NavOptions? = null) {
    navigate(
        route = FilteringTwo,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringThreeNavGraph(
    navHostController: NavHostController
) {
    composable<FilteringTwo> {
        FilteringThreeScreen(
            navController = navHostController
        )
    }
}

@Serializable
data object FilteringThree : Route