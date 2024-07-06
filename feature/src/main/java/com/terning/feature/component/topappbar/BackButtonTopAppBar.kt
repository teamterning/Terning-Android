package com.terning.feature.component.topappbar

import androidx.compose.runtime.Composable
import com.terning.feature.main.MainNavigator

@Composable
fun BackButtonTopAppBar(
    title: String, navigator: MainNavigator,
) {
    TerningTopAppBar(
        title = title,
        showBackButton = true,
        onBackButtonClick = { navigator.navigateUp() },
    )
}