package com.terning.core.designsystem.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.terning.core.R
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.WarningRed
import com.terning.core.util.NAME_ERROR_REGEX

data class NameFieldState(
    val name: String,
    val lineColor: Color,
    val helperMessage: Int,
    val helperIcon: Int?,
    val helperColor: Color,
    val isValid: Boolean
)

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    onValidationChanged: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val nameErrorRegex = NAME_ERROR_REGEX
    val trimmedName: String
    var outOfBoundName = false
    if (value.length > MAX_LENGTH) {
        trimmedName = value.substring(0, MAX_LENGTH)
        outOfBoundName = true
    } else {
        trimmedName = value
    }

    val state = when {
        nameErrorRegex.matcher(trimmedName).find() -> {
            NameFieldState(
                name = trimmedName,
                lineColor = WarningRed,
                helperMessage = R.string.sign_up_helper_error,
                helperIcon = R.drawable.ic_name_text_field_error,
                helperColor = WarningRed,
                isValid = false
            )
        }

        trimmedName.isEmpty() || trimmedName.isBlank() -> {
            NameFieldState(
                name = trimmedName,
                lineColor = Grey500,
                helperMessage = R.string.sign_up_helper,
                helperIcon = null,
                helperColor = Grey400,
                isValid = false
            )
        }

        outOfBoundName -> {
            NameFieldState(
                name = trimmedName,
                lineColor = WarningRed,
                helperMessage = R.string.sign_up_helper_out,
                helperIcon = R.drawable.ic_name_text_field_error,
                helperColor = WarningRed,
                isValid = false
            )
        }

        else -> {
            NameFieldState(
                name = trimmedName,
                lineColor = TerningMain,
                helperMessage = R.string.sign_up_helper_available,
                helperIcon = R.drawable.ic_name_text_field_check,
                helperColor = TerningMain,
                isValid = true
            )
        }
    }

    onValidationChanged(state.isValid)

    TerningBasicTextField(
        value = state.name,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TerningTheme.typography.detail1,
        textColor = Black,
        drawLineColor = state.lineColor,
        cursorBrush = SolidColor(Grey400),
        hint = hint,
        hintColor = Grey300,
        showTextLength = true,
        maxTextLength = MAX_LENGTH,
        helperMessage = stringResource(id = state.helperMessage),
        helperIcon = state.helperIcon,
        helperColor = state.helperColor,
        imeAction = ImeAction.Done,
        onDoneAction = {
            focusManager.clearFocus()
            keyboardController?.hide()
        }
    )
}

private const val MAX_LENGTH = 12
