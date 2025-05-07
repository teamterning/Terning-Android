package com.terning.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.terning.core.designsystem.type.NotificationRedirect
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

    fun getStartDestination(redirect: String?, host: String?, internId: String?) =
        when (NotificationRedirect.from(host)) {
            NotificationRedirect.SEARCH -> Search
            NotificationRedirect.HOME -> Home
            NotificationRedirect.CALENDAR -> Calendar
            NotificationRedirect.INTERN -> Splash(redirect = redirect, internId = internId)
            else -> Splash(redirect = redirect)
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
            MainTab.HOME -> navController.navigateHome(navOptions)
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