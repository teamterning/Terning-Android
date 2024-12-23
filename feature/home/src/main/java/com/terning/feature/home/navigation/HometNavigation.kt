package com.terning.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.home.HomeRoute
import kotlinx.serialization.Serializable

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    navigate(
        route = Home,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
    navigateToCalendar: () -> Unit,
    navigateToIntern: (Long) -> Unit
) {
    composable<Home> {
        HomeRoute(
            paddingValues = paddingValues,
            navigateToCalendar = navigateToCalendar,
            navigateToIntern = navigateToIntern
        )
    }
}

@Serializable
data object Home : MainTabRoute