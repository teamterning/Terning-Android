package com.terning.core.designsystem.component.topappbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BackButtonTopAppBar(
    title: String,
    modifier: Modifier,
    onBackButtonClick: (() -> Unit),
) {
    TerningBasicTopAppBar(
        title = title,
        showBackButton = true,
        modifier = modifier,
        onBackButtonClick = { onBackButtonClick() },
    )
}