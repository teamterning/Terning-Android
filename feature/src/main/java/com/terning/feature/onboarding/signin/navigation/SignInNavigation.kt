package com.terning.feature.onboarding.signin.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.home.home.navigation.navigateHome
import com.terning.feature.onboarding.signin.SignInRoute
import com.terning.feature.onboarding.signup.navigation.navigateSignUp
import kotlinx.serialization.Serializable

fun NavController.navigateSignIn(navOptions: NavOptions? = null) {
    navigate(
        route = SignIn,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signInNavGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
) {
    composable<SignIn> {
        SignInRoute(
            paddingValues = paddingValues,
            navigateToHome = { navHostController.navigateHome() },
            navigateToSignUp = { authId -> navHostController.navigateSignUp(authId) }
        )
    }
}

@Serializable
data object SignIn : Route