package com.terning.core.designsystem.textfield

import androidx.compose.runtime.Composable
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTypography

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,
    leftIcon: Int,
) {
    TerningTextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = TerningTypography().button3,
        drawLineColor = TerningMain,
        strokeWidth = 2f,
        hint = hint,
        hintColor = Grey400,
        leftIcon = leftIcon,
    )
}