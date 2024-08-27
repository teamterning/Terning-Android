package com.terning.feature.mypage.profileedit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.Route
import com.terning.feature.mypage.profileedit.ProfileEditRoute
import kotlinx.serialization.Serializable

fun NavController.navigateProfileEdit(navOptions: NavOptions? = null) {
    navigate(
        route = ProfileEdit,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.profileEditNavGraph(
    navHostController: NavHostController,
) {
    composable<ProfileEdit>(
    ) {
        ProfileEditRoute()
    }
}

@Serializable
data object ProfileEdit : Route