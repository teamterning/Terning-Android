package com.terning.feature.component.topappbar

import androidx.compose.runtime.Composable

@Composable
fun BackWithTitleTopAppBar(onBackButtonClick: () -> Unit) {
    TerningTopAppBar(
        showBackButton = true,
        onBackButtonClick = { onBackButtonClick.invoke() })
}