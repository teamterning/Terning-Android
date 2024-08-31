package com.terning.feature.mypage.profileedit

data class ProfileEditState(
    val name: String = "",
    val character: Int = 0,
    val isButtonValid: Boolean = true,
    val authType: String = "",
    val showBottomSheet: Boolean = false
)