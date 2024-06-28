//package com.terning.feature.main
//
//import androidx.compose.runtime.Composable
//import com.terning.core.navigation.MainTabRoute
//import com.terning.core.navigation.Route
//import com.terning.feature.R
//
//internal enum class MainTab(
//    val iconResId: Int,
//    internal val contentDescription: String,
//    val route: MainTabRoute,
//) {
//    First(
//        iconResId = R.drawable.ic_launcher_background,
//        contentDescription = "첫 번째 화면",
//        First
//    ),
//    Mock(
//        iconResId = R.drawable.ic_launcher_background,
//        contentDescription = "Mock 화면",
//        Mock,
//    )
//
//    companion object {
//        @Composable
//        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? =
//            entries.find { predicate(it.route) }
//
//
//        @Composable
//        fun contains(predicate: @Composable (Route) -> Boolean): Boolean =
//            entries.map { it.route }.any { predicate(it) }
//
//    }
//}