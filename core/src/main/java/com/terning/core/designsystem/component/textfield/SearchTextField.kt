package com.terning.core.designsystem.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,
    leftIcon: Int,
    readOnly: Boolean = false,
) {
    TerningBasicTextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = TerningTheme.typography.button3,
        textColor = Grey400,
        cursorBrush = SolidColor(Grey300),
        drawLineColor = TerningMain,
        strokeWidth = 2f,
        hint = hint,
        hintColor = Grey300,
        leftIcon = leftIcon,
        leftIconColor = TerningMain,
        readOnly = readOnly,
        helperColor = TerningMain
    )
}