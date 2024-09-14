package com.terning.feature.onboarding.signin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
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
    navHostController: NavHostController,
) {
    composable<SignIn> {
        val navOptions = navOptions {
            popUpTo(SignIn) {
                inclusive = true
            }
            launchSingleTop = true
        }
        SignInRoute(
            navigateToHome = {
                navHostController.navigateHome(navOptions = navOptions)
            },
            navigateToSignUp = { authId ->
                navHostController.navigateSignUp(authId = authId, navOptions = navOptions)
            }
        )
    }
}

@Serializable
data object SignIn : Route