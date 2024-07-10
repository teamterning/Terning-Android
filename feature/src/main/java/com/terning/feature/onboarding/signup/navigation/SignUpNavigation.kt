package com.terning.feature.onboarding.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.signup.SignUpRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSignUp(navOptions: NavOptions? = null) {
    navigate(
        route = SignUp,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signUpNavGraph(
    navHostController: NavHostController
) {
    composable<SignUp> {
        SignUpRoute(navController = navHostController)
    }
}

@Serializable
data object SignUp : Route