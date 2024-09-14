package com.terning.feature.onboarding.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.filtering.startfiltering.navigation.navigateStartFiltering
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
    navHostController: NavHostController
) {
    composable<SignUp> {
        val args = it.toRoute<SignUp>()
        val navOptions = navOptions {
            popUpTo(id = navHostController.graph.id) {
                inclusive = true
            }
        }
        SignUpRoute(
            authId = args.authId,
            navigateToStartFiltering = { name ->
                navHostController.navigateStartFiltering(
                    name = name,
                    navOptions = navOptions
                )
            }
        )
    }
}

@Serializable
data class SignUp(
    val authId: String
) : Route