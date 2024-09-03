package com.terning.feature.filtering.filteringtwo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.filtering.filteringtwo.FilteringTwoScreen
import com.terning.feature.filtering.filteringthree.navigation.navigateFilteringThree
import kotlinx.serialization.Serializable

fun NavController.navigateFilteringTwo(
    grade: Int,
    navOptions: NavOptions? = null
) {
    navigate(
        route = FilteringTwo(grade = grade),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.filteringTwoNavGraph(
    navHostController: NavHostController
) {
    composable<FilteringTwo> {
        val args = it.toRoute<FilteringTwo>()
        FilteringTwoScreen(
            grade = args.grade,
            onNextClick = { _, workingPeriod ->
                navHostController.navigateFilteringThree(
                    grade = args.grade,
                    workingPeriod = workingPeriod
                )
            },
            navigateUp = { navHostController.navigateUp() }
        )
    }
}

@Serializable
data class FilteringTwo(
    val grade: Int
) : Route