package com.terning.feature.onboarding.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.signup.SignUpRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSignUp(
    navOptions: NavOptions? = null,
    authId: String
) {
    navigate(
        route = SignUp(authId),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signUpNavGraph(
    navHostController: NavHostController
) {
    composable<SignUp> {
        val args = it.toRoute<SignUp>()
        SignUpRoute(
            navController = navHostController,
            authId = args.authId
        )
    }
}

@Serializable
data class SignUp(
    val authId: String
) : Route