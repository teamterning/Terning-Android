package com.terning.feature.mypage.mypage

import androidx.annotation.StringRes

sealed class MyPageSideEffect {
    data object NavigateToProfileEdit : MyPageSideEffect()
    data object NavigateToNoticeWebView : MyPageSideEffect()
    data object NavigateToOpinionWebView : MyPageSideEffect()
    data object NavigateToServiceWebView : MyPageSideEffect()
    data object NavigateToPersonalWebView : MyPageSideEffect()
    data class ShowToast(@StringRes val message: Int) : MyPageSideEffect()
}