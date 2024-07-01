package com.terning.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.terning.core.navigation.MainTabRoute
import com.terning.core.navigation.Route
import com.terning.feature.R
import com.terning.feature.first.navigation.First
import com.terning.feature.mock.navigation.Mock


enum class MainTab(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute,
) {
    FIRST(
        icon = R.drawable.ic_launcher_foreground,
        contentDescription = R.string.first,
        route = First
    ),
    MOCK(
        icon = R.drawable.ic_launcher_foreground,
        contentDescription = R.string.mock,
        route = Mock
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
