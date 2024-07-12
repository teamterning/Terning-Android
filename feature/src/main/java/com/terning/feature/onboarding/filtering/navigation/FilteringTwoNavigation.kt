@file:JvmName("FilteringThreeNavigationKt")

package com.terning.feature.onboarding.filtering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.filtering.FilteringTwoScreen
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringTwo(navOptions: NavOptions? = null) {
    navigate(
        route = FilteringTwo,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringTwoNavGraph(
    navHostController: NavHostController
) {
    composable<FilteringTwo> {
        FilteringTwoScreen(
            navController = navHostController
        )
    }
}

@Serializable
data object FilteringTwo : Route