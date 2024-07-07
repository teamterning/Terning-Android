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
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTypography
import com.terning.feature.R

@Composable
fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        TerningTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            textStyle = TerningTypography().button3,
            drawLineColor = TerningMain,
            strokeWidth = 2f,
            hint = "힌트텍스트",
            hintColor = Grey400,
            leftIcon = R.drawable.ic_nav_search,
        )
    }
}