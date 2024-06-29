package com.terning.feature.mock.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.mock.MockRoute
import kotlinx.serialization.Serializable

fun NavController.navigateMock(navOptions: NavOptions? = null) {
    navigate(
        route = Mock,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.mockNavGraph() {
    composable<Mock> {
        MockRoute()
    }
}

@Serializable
data object Mock : MainTabRoute