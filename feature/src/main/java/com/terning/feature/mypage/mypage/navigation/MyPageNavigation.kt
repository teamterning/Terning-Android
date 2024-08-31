package com.terning.feature.mypage.mypage.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
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
) {
    composable<MyPage>{
        MyPageRoute(
            paddingValues = paddingValues,
            navigateToProfileEdit = { name -> navHostController.navigateProfileEdit(name) }
        )
    }
}

@Serializable
data object MyPage : MainTabRoute