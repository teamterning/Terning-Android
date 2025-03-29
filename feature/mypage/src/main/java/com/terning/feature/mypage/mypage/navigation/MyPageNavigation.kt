package com.terning.feature.mypage.mypage.navigation

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.mypage.mypage.MyPageRoute
import com.terning.feature.mypage.profileedit.navigation.navigateProfileEdit
import kotlinx.serialization.Serializable

fun NavController.navigateMyPage(navOptions: NavOptions? = null) {
    navigate(
        route = MyPage,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.myPageNavGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    restartApp: () -> Unit
) {
    composable<MyPage>(
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "terning://mypage"
                action = Intent.ACTION_VIEW
                mimeType = "*/*"
            }
        )
    ) {
        MyPageRoute(
            paddingValues = paddingValues,
            navigateToProfileEdit = { name, profileImage, authType ->
                navHostController.navigateProfileEdit(
                    name,
                    profileImage,
                    authType
                )
            },
            restartApp = restartApp
        )
    }
}

@Serializable
data object MyPage : MainTabRoute