package com.terning.core.designsystem.component.textfield

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    drawLineColor: Color,
    @StringRes helperMessage: Int,
    helperColor: Color,
    modifier: Modifier = Modifier,
    helperIcon: Int? = null,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

//    val nameErrorRegex = Regex(NAME_ERROR)
//    var trimmedName = ""
//    var outOfBoundName = false
//    if (name.length > MAX_LENGTH) {
//        trimmedName = name.substring(0, MAX_LENGTH)
//        outOfBoundName = true
//    } else trimmedName = name

    TerningBasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TerningTheme.typography.detail1,
        textColor = Black,
        drawLineColor = drawLineColor,
        cursorBrush = SolidColor(Grey400),
        hint = hint,
        hintColor = Grey300,
        showTextLength = true,
        maxTextLength = 12,
        helperMessage = stringResource(id = helperMessage),
        helperIcon = helperIcon,
        helperColor = helperColor,
        imeAction = ImeAction.Done,
        onDoneAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    )
}
