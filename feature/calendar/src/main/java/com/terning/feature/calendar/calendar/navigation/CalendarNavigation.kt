package com.terning.feature.calendar.calendar.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
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
    navigateIntern: (Long) -> Unit,
    paddingValues: PaddingValues
) {
    composable<Calendar> {
        CalendarRoute(
            modifier = Modifier.padding(paddingValues),
            navigateToAnnouncement = navigateIntern
        )
    }
}

@Serializable
data object Calendar : MainTabRoute