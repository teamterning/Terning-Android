package com.terning.feature.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.terning.core.designsystem.util.DeeplinkDefaults
import com.terning.core.navigation.MainTabRoute
import com.terning.feature.home.HomeRoute
import kotlinx.serialization.Serializable

fun NavController.navigateHome(navOptions: NavOptions? = null) {
    navigate(
        route = Home,
        navOptions = navOptions
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
    navigateToCalendar: () -> Unit,
    navigateToIntern: (Long) -> Unit
) {
    composable<Home>(
        deepLinks = listOf(
            navDeepLink<Home>(
                basePath = DeeplinkDefaults.build("home")
            )
        )
    ) {
        HomeRoute(
            paddingValues = paddingValues,
            navigateToCalendar = navigateToCalendar,
            navigateToIntern = navigateToIntern
        )
    }
}

@Serializable
data object Home : MainTabRoute