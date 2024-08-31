package com.terning.feature.onboarding.signup

data class SignUpState(
    val name: String = "",
    val character: Int = 0,
    val isButtonValid: Boolean = false,
    val authId: String = ""
)