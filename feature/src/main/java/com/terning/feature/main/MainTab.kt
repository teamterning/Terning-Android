package com.terning.feature.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.terning.feature.R

enum class MainTab(
    val icon: ImageVector,
    val contentDescription: Int,
    val route: String,
) {
    FIRST(
        icon = Icons.Filled.Home,
        contentDescription = R.string.first,
        "FIRST"
    ),
    MOCK(
        icon = Icons.Filled.Search,
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