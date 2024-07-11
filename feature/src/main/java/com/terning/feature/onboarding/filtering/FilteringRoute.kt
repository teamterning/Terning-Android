package com.terning.feature.onboarding.filtering

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun FilteringRoute(
    navController: NavController
) {
    FilteringScreen()
}

@Composable
fun FilteringScreen() {
    Text(text = "filtering")
}
