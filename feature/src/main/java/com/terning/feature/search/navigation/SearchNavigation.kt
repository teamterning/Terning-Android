package com.terning.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.calendar.CalendarRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSearch(navOptions: NavOptions? = null) {
    navigate(
        route = Search,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.searchNavGraph() {
    composable<Search> {
        CalendarRoute()
    }
}

@Serializable
data object Search : MainTabRoute