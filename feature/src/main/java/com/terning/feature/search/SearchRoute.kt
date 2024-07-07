package com.terning.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.terning.core.designsystem.topappbar.LogoTopAppBar

@Composable
fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "탐색 스크린")
    }
}
