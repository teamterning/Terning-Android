package com.terning.feature.component.topappbar

import androidx.compose.runtime.Composable

@Composable
fun BackWithTitleTopAppBar(title: String, onBackButtonClick: () -> Unit) {
    TerningTopAppBar(
        title = title,
        showBackButton = true,
        onBackButtonClick = { onBackButtonClick.invoke() })
}