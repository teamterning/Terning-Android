package com.terning.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.terning.core.navigation.MainTabRoute
import com.terning.core.navigation.Route
import com.terning.feature.R
import com.terning.feature.calendar.calendar.navigation.Calendar
import com.terning.feature.home.home.navigation.Home
import com.terning.feature.mypage.mypage.navigation.MyPage
import com.terning.feature.search.search.navigation.Search

enum class MainTab(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute,
) {
    HOME(
        icon = R.drawable.ic_nav_home,
        contentDescription = R.string.bottom_nav_home,
        route = Home
    ),
    CALENDAR(
        icon = R.drawable.ic_nav_calendar,
        contentDescription = R.string.bottom_nav_calendar,
        route = Calendar
    ),
    SEARCH(
        icon = R.drawable.ic_nav_search,
        contentDescription = R.string.bottom_nav_search,
        route = Search
    ),
    MY_PAGE(
        icon = R.drawable.ic_nav_my_page,
        contentDescription = R.string.bottom_nav_my_page,
        route = MyPage
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
