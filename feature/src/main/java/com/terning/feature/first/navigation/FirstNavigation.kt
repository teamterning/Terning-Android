package com.terning.feature.first.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.first.FirstRoute
import kotlinx.serialization.Serializable

fun NavController.navigateFirst(navOptions: NavOptions? = null) {
    navigate(
        route = First,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.firstNavGraph() {
    composable<First> {
        FirstRoute()
    }
}

@Serializable
data object First : MainTabRoute