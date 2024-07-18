package com.terning.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.mypage.MyPageRoute
import kotlinx.serialization.Serializable

fun NavController.navigateMyPage(navOptions: NavOptions? = null) {
    navigate(
        route = MyPage,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.myPageNavGraph(
    navHostController: NavHostController
) {
    composable<MyPage> {
        MyPageRoute(navController = navHostController)
    }
}

@Serializable
data object MyPage : MainTabRoute