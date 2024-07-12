package com.terning.feature.intern.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.intern.InternRoute
import kotlinx.serialization.Serializable

fun NavController.navigateIntern(navOptions: NavOptions? = null) {
    navigate(
        route = Intern,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.internNavGraph(
    navHostController: NavHostController,
) {
    composable<Intern> {
        InternRoute(
            navController = navHostController
        )
    }
}

@Serializable
data object Intern : Route