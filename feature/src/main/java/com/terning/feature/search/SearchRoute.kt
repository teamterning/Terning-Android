package com.terning.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.component.TerningTextField

@Composable
fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "탐색 스크린")
        TerningTextField(value = "") {

        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}