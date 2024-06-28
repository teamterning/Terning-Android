package com.terning.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.terning.feature.first.navigation.First
import com.terning.feature.first.navigation.navigateFirst
import com.terning.feature.mock.navigation.navigateMock

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = First.ROUTE

    val currentTab: MainTab?
        @Composable get() = currentDestination?.route?.let(MainTab::find)

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.FIRST -> navController.navigateFirst(navOptions)
            MainTab.MOCK -> navController.navigateMock(navOptions)
        }
    }

    fun navigateToFirst() {
        navController.navigateFirst()
    }

    fun navigateToMock() {
        navController.navigateMock()
    }

    private fun navigateUp() {
        navController.navigateUp()
    }

    @Composable
    fun showBottomBar(): Boolean {
        val currentRoute = currentDestination ?: return false
        return currentRoute.route in MainTab
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
