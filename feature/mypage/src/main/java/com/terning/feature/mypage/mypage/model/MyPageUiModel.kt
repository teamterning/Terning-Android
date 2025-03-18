package com.terning.feature.mypage.mypage.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

sealed interface MyPageUiModel {
    @Immutable
    data class Header(
        val title: String
    ) : MyPageUiModel

    @Immutable
    data class MyPageItem(
        val leadingIcon: Int,
        val text: String,
        val onItemClick: () -> Unit,
        val trailingContent: @Composable () -> Unit,
    ) : MyPageUiModel

    @Immutable
    data object HorizontalDivider : MyPageUiModel
}