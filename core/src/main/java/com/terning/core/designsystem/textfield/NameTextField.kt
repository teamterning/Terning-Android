package com.terning.core.designsystem.textfield

import androidx.compose.runtime.Composable
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningTypography

@Composable
fun NameTextField(
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,
    helperMessage: String,
) {
    TerningTextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = TerningTypography().detail1,
        textColor = Black,
        drawLineColor = Grey500,
        hint = hint,
        hintColor = Grey300,
        showTextLength = true,
        maxTextLength = 12,
        helperMessage = helperMessage
    )
}