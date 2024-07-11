package com.terning.feature.home.changefilter

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ChangeFilterRoute(
    navController: NavController,
) {
    ChangeFilterScreen()
}

@Composable
fun ChangeFilterScreen() {
    Text(text = "필터링 재설정")
}