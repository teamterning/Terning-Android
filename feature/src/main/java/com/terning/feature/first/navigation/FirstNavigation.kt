package com.terning.feature.first.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.feature.first.FirstRoute


fun NavController.navigateFirst(navOptions: NavOptions? = null) {
    navigate(
        route = First.ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.firstNavGraph() {
    composable(route = First.ROUTE) {
        FirstRoute()
    }
}

object First {
    const val ROUTE = "FIRST"
}