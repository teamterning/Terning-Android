package com.terning.core.designsystem.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun SearchTextField(
    text: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier,
    hint: String,
    leftIcon: Int,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onSearchAction: () -> Unit? = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TerningBasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TerningTheme.typography.button3,
        textColor = Grey400,
        cursorBrush = SolidColor(Grey300),
        drawLineColor = TerningMain,
        strokeWidth = 2f,
        hint = hint,
        hintColor = Grey300,
        leftIcon = leftIcon,
        leftIconColor = TerningMain,
        imeAction = ImeAction.Search,
        enabled = enabled,
        readOnly = readOnly,
        onSearchAction = {
            if (text.isNotBlank()) {
                keyboardController?.hide()
                focusManager.clearFocus()
                onSearchAction()
            }
        },
        helperColor = TerningMain
    )
}