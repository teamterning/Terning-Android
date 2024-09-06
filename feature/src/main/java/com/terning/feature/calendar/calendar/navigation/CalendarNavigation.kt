package com.terning.feature.calendar.calendar.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.calendar.calendar.CalendarRoute
import com.terning.feature.intern.navigation.navigateIntern
import kotlinx.serialization.Serializable


fun NavController.navigateCalendar(navOptions: NavOptions? = null) {
    navigate(
        route = Calendar,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.calendarNavGraph(
    navHostController: NavController,
    paddingValues: PaddingValues
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
            modifier = Modifier.padding(paddingValues),
            navigateUp = navHostController::navigateUp,
            navigateToAnnouncement = navHostController::navigateIntern
        )
    }
}

@Serializable
data object Calendar : MainTabRoute