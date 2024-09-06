package com.terning.feature.mypage.mypage

sealed class MyPageSideEffect {
    data object NavigateToProfileEdit : MyPageSideEffect()
}