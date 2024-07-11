package com.terning.feature.onboarding.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.signin.SignInRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSignIn(navOptions: NavOptions? = null) {
    navigate(
        route = SignIn,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signInNavGraph(
    navHostController: NavHostController
) {
    composable<SignIn> {
        SignInRoute(
            navController = navHostController
        )
    }
}

@Serializable
data object SignIn : Route