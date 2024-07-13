package com.terning.core.designsystem.component.topappbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BackButtonTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackButtonClick: (() -> Unit),
    actions: List<@Composable () -> Unit> = emptyList(),
) {
    TerningBasicTopAppBar(
        title = title,
        showBackButton = true,
        modifier = modifier,
        onBackButtonClick = { onBackButtonClick() },
        actions = actions
    )
}