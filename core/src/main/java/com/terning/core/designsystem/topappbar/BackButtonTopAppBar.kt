package com.terning.core.designsystem.topappbar

import androidx.compose.runtime.Composable

@Composable
fun BackButtonTopAppBar(
    title: String, onBackButtonClick: (() -> Unit),
) {
    TerningBasicTopAppBar(
        title = title,
        showBackButton = true,
        onBackButtonClick = { onBackButtonClick.invoke() },
    )
}