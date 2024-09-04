package com.terning.feature.mypage.profileedit

sealed class ProfileEditSideEffect {
    data object NavigateUp : ProfileEditSideEffect()
}