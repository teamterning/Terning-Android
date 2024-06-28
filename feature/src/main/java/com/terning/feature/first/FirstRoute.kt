package com.terning.feature.first

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FirstRoute() {
    FirstScreen()
}

@Composable
fun FirstScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "This is FirstScreen")
    }
}