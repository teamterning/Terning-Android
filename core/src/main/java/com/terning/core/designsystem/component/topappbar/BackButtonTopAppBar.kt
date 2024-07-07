package com.terning.core.designsystem.component.topappbar

import androidx.compose.runtime.Composable

@Composable
fun BackButtonTopAppBar(
    title: String, onBackButtonClick: (() -> Unit),
) {
    TerningTopAppBar(
        title = title,
        showBackButton = true,
        onBackButtonClick = { onBackButtonClick.invoke() },
    )
}