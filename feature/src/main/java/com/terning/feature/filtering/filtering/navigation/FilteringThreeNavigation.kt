package com.terning.feature.filtering.filtering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.filtering.filtering.FilteringThreeRoute
import com.terning.feature.filtering.filtering.FilteringThreeScreen
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringThree(
    grade : Int,
    workingPeriod : Int,
    navOptions: NavOptions? = null) {
    navigate(
        route = FilteringThree(grade = grade, workingPeriod = workingPeriod),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringThreeNavGraph(
    navHostController: NavHostController
) {
    composable<FilteringThree> {
        val args = it.toRoute<FilteringThree>()
        FilteringThreeRoute(
            grade = args.grade,
            workingPeriod = args.workingPeriod,
            navigateUp = {navHostController.navigateUp()},
            navController = navHostController
        )
    }
}

@Serializable
data class FilteringThree(
    val grade : Int,
    val workingPeriod: Int
) : Route