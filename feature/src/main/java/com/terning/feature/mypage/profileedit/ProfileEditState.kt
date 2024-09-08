package com.terning.feature.mypage.profileedit

data class ProfileEditState(
    val name: String = "",
    val initialName: String = "",
    val profile: String = "",
    val initialProfile: String = "",
    val initialView: Boolean = true,
    val isButtonValid: Boolean = false,
    val authType: String = "",
    val showBottomSheet: Boolean = false
)