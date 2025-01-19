package com.terning.feature.onboarding.signup

data class SignUpState(
    val name: String = "",
    val profileImage: String = "basic",
    val isButtonValid: Boolean = false,
    val authId: String = "",
    val showBottomSheet: Boolean = false
)