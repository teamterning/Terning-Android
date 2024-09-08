package com.terning.feature.mypage.profileedit

import androidx.annotation.StringRes

sealed class ProfileEditSideEffect {
    data object NavigateUp : ProfileEditSideEffect()
    data class ShowToast(@StringRes val message: Int) : ProfileEditSideEffect()
}