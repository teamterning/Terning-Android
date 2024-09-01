package com.terning.feature.mypage.profileedit

data class ProfileEditState(
    val name: String = "",
    val initialName: String = "",
    val profile: Int = 0,
    val initialProfile: Int = 0,
    val isInfoChange: Boolean = false,
    val isButtonValid: Boolean = false,
    val authType: String = "",
    val showBottomSheet: Boolean = false
)