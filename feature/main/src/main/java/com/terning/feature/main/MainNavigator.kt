package com.terning.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.terning.core.designsystem.type.DeeplinkType
import com.terning.feature.calendar.calendar.navigation.Calendar
import com.terning.feature.calendar.calendar.navigation.navigateCalendar
import com.terning.feature.home.navigation.Home
import com.terning.feature.home.navigation.navigateHome
import com.terning.feature.mypage.mypage.navigation.navigateMyPage
import com.terning.feature.search.search.navigation.Search
import com.terning.feature.search.search.navigation.navigateSearch
import com.terning.feature.splash.navigation.Splash

class MainNavigator(
    val navController: NavHostController,
) {

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun getStartDestination(
        host: String?,
        action: String?,
        id: String?
    ) = when (DeeplinkType.from(host)) {
        DeeplinkType.SEARCH -> Search
        DeeplinkType.HOME -> Home(internId = null)
        DeeplinkType.CALENDAR -> Calendar
        DeeplinkType.KAKAOLINK -> Splash(action = action, id = id)

        else -> Splash(action = action)
    }

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateHome(navOptions = navOptions, internId = null)
            MainTab.CALENDAR -> navController.navigateCalendar(navOptions)
            MainTab.SEARCH -> navController.navigateSearch(navOptions)
            MainTab.MY_PAGE -> navController.navigateMyPage(navOptions)
        }
    }

    @Composable
    fun showBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}