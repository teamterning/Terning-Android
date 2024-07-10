package com.terning.feature.onboarding.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SignUpRoute() {
    SignUpScreen()
}

@Composable
fun SignUpScreen() {

    Text(text = "반가워요!\n" +
            "이름을 알려주세요")
}