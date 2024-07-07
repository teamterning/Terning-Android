package com.terning.core.designsystem.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.SolidColor
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
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
        textColor = Grey400,
        cursorBrush = SolidColor(Grey300),
        drawLineColor = Grey300,
        strokeWidth = 2f,
        hint = hint,
        hintColor = Grey300,
        leftIcon = leftIcon,
        leftIconColor = Grey400,
    )
}