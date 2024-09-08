package com.terning.feature.mypage.profileedit.navigation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.terning.core.navigation.Route
import com.terning.feature.mypage.profileedit.ProfileEditRoute
import kotlinx.serialization.Serializable

fun NavController.navigateProfileEdit(
    name: String,
    profileImage: String,
    navOptions: NavOptions? = null
) {
    navigate(
        route = ProfileEdit(name = name, profileImage = profileImage),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.profileEditNavGraph(
    navHostController: NavHostController,
) {
    composable<ProfileEdit>(
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
            )
        },
    ) {
        val args = it.toRoute<ProfileEdit>()
        ProfileEditRoute(
            initialName = args.name,
            initialProfile = args.profileImage,
            navigateUp = { navHostController.navigateUp() }
        )
    }
}

@Serializable
data class ProfileEdit(
    val name: String,
    val profileImage: String
) : Route