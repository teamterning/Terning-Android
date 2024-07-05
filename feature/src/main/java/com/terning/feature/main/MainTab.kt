package com.terning.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.terning.core.navigation.MainTabRoute
import com.terning.core.navigation.Route
import com.terning.feature.R

enum class MainTab(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute,
) {
    Home(
        icon = R.drawable.ic_nav_home,
        contentDescription = R.string.bottom_nav_home,
        route = com.terning.feature.home.navigation.Home
    ),
    Calendar(
        icon = R.drawable.ic_nav_calendar,
        contentDescription = R.string.bottom_nav_calendar,
        route = com.terning.feature.calendar.navigation.Calendar
    ),
    Search(
        icon = R.drawable.ic_nav_search,
        contentDescription = R.string.bottom_nav_search,
        route = com.terning.feature.search.navigation.Search
    ),
    MyPage(
        icon = R.drawable.ic_nav_my_page,
        contentDescription = R.string.bottom_nav_my_page,
        route = com.terning.feature.myPage.navigation.MyPage
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }

    }
}
