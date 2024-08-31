package com.terning.feature.mypage.profileedit.navigation

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
    navOptions: NavOptions? = null
) {
    navigate(
        route = ProfileEdit(name = name),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.profileEditNavGraph(
    navHostController: NavHostController,
) {
    composable<ProfileEdit> {
        val args = it.toRoute<ProfileEdit>()
        ProfileEditRoute(
            initialName = args.name,
            navigateUp = { navHostController.navigateUp() }
        )
    }
}

@Serializable
data class ProfileEdit(
    val name: String
) : Route