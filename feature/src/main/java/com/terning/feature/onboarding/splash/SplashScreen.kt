package com.terning.feature.onboarding.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.feature.R
import com.terning.feature.onboarding.signin.navigation.navigateSignIn
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    var isNavigate by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        delay(1000)
        isNavigate = true
    }
    TerningImage(painter = R.drawable.ic_splash)

    if (isNavigate) navController.navigateSignIn()
}
