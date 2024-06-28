package com.terning.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.terning.feature.R

enum class MainTab(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val route: String,
) {
    FIRST(
        icon = R.drawable.ic_launcher_foreground,
        contentDescription = R.string.first,
        "FIRST"
    ),
    MOCK(
        icon = R.drawable.ic_launcher_foreground,
        contentDescription = R.string.mock,
        "MOCK",
    );

    companion object {
        operator fun contains(route: String?) =
            entries.map { itemType -> itemType.route }.contains(route)

        fun find(route: String): MainTab? =
            entries.find { itemType -> itemType.route == route }
    }
}