package com.terning.feature.mypage.mypage

import androidx.annotation.StringRes

sealed class MyPageSideEffect {
    data object NavigateToProfileEdit : MyPageSideEffect()
    data class ShowToast(@StringRes val message: Int) : MyPageSideEffect()
}