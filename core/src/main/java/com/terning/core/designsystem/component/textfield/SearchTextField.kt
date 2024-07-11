package com.terning.core.designsystem.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain

@Composable
fun SearchTextField(
    text: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier,
    textStyle: TextStyle,
    hint: String,
    leftIcon: Int,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onDoneAction: () -> Unit? = {},
) {
    TerningBasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        textColor = Grey400,
        cursorBrush = SolidColor(Grey300),
        drawLineColor = TerningMain,
        strokeWidth = 2f,
        hint = hint,
        hintColor = Grey300,
        leftIcon = leftIcon,
        leftIconColor = TerningMain,
        enabled = enabled,
        readOnly = readOnly,
        onDoneAction = onDoneAction
    )
}