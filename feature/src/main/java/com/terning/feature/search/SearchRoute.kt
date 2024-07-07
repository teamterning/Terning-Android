package com.terning.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.textfield.TerningTextField

@Composable
fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "탐색 스크린")
        TerningTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            }
        )
    }
}