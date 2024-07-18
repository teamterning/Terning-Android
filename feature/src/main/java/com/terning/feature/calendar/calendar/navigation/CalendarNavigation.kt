package com.terning.feature.calendar.calendar.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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

fun NavGraphBuilder.calendarNavGraph(
    navHostController: NavController
) {
    composable<Calendar>(
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
        CalendarRoute(
            navController = navHostController
        )
    }
}

@Serializable
data object Calendar : MainTabRoute