package com.terning.feature.mock.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.feature.mock.MockRoute

fun NavController.navigateMock(navOptions: NavOptions? = null) {
    navigate(
        route = Mock.ROUTE,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.mockNavGraph() {
    composable(route = Mock.ROUTE) {
        MockRoute()
    }
}

object Mock {
    const val ROUTE = "MOCK"
}