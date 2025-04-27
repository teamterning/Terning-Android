package com.terning.feature.mypage.mypage.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
internal sealed interface MyPageUiModel {
    @Immutable
    data class Header(
        val text: Int
    ) : MyPageUiModel

    @Immutable
    data class MyPageItem(
        val leadingIcon: Int,
        val text: Int,
        val onItemClick: () -> Unit = {},
        val trailingContent: @Composable () -> Unit = {},
    ) : MyPageUiModel

    @Immutable
    data object HorizontalDivider : MyPageUiModel
}