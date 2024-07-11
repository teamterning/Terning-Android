package com.terning.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier,
        topBar = {
            LogoTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(text = "홈 스크린")
        }
    }
}