package com.terning.feature.onboarding.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.onboarding.signup.SignUpRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSignUp(
    authId: String,
    navOptions: NavOptions? = null,
) {
    navigate(
        route = SignUp(authId = authId),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signUpNavGraph(
    navigateStartFiltering: (String) -> Unit
) {
    composable<SignUp> {
        val args = it.toRoute<SignUp>()
        SignUpRoute(
            authId = args.authId,
            navigateToStartFiltering = navigateStartFiltering
        )
    }
}

@Serializable
data class SignUp(
    val authId: String
) : Route