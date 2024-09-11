package com.terning.feature.filtering.filteringthree.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.filtering.filteringthree.FilteringThreeRoute
import com.terning.feature.filtering.starthome.navigation.navigateStartHome
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringThree(
    grade: String,
    workingPeriod: String,
    navOptions: NavOptions? = null
) {
    navigate(
        route = FilteringThree(grade = grade, workingPeriod = workingPeriod),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringThreeNavGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    composable<FilteringThree> {
        val args = it.toRoute<FilteringThree>()
        FilteringThreeRoute(
            paddingValues = paddingValues,
            grade = args.grade,
            workingPeriod = args.workingPeriod,
            navigateUp = navHostController::navigateUp,
            navigateToStartHome = navHostController::navigateStartHome
        )
    }
}

@Serializable
data class FilteringThree(
    val grade: String,
    val workingPeriod: String
) : Route