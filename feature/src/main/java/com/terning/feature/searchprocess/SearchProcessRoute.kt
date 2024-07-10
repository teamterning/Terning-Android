package com.terning.feature.searchprocess

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SearchProcessRoute() {
    SearchProcessScreen()
}

@Composable
fun SearchProcessScreen() {
    var text by remember { mutableStateOf("") }
}