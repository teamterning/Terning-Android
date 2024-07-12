package com.terning.feature.calendar.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.calendar.calendar.CalendarRoute
import kotlinx.serialization.Serializable


fun NavController.navigateCalendar(navOptions: NavOptions? = null) {
    navigate(
        route = Calendar,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.calendarNavGraph() {
    composable<Calendar> {
        CalendarRoute()
    }
}

@Serializable
data object Calendar : MainTabRoute