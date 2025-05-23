package com.terning.core.designsystem.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.terning.core.designsystem.R
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.WarningRed
import com.terning.core.designsystem.theme.White
import com.terning.core.designsystem.util.NAME_ERROR_REGEX

data class NameFieldState(
    val name: String,
    val lineColor: Color,
    val helperMessage: Int,
    val helperIcon: Int?,
    val helperColor: Color,
    val isButtonValid: Boolean
)

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    onValidationChanged: (Boolean) -> Unit,
    initialView: Boolean = false,
    isProfileChangedButNameSame: Boolean = false,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val nameErrorRegex = NAME_ERROR_REGEX
    val trimmedName: String
    var isNameOutOfBounds = false
    if (value.length > MAX_LENGTH) {
        trimmedName = value.substring(0, MAX_LENGTH)
        isNameOutOfBounds = true
    } else {
        trimmedName = value
    }

    val state = when {
        initialView -> {
            NameFieldState(
                name = trimmedName,
                lineColor = Grey500,
                helperMessage = R.string.sign_up_helper,
                helperIcon = R.drawable.ic_name_text_field_check,
                helperColor = White,
                isButtonValid = false
            )
        }

        isProfileChangedButNameSame -> {
            NameFieldState(
                name = trimmedName,
                lineColor = Grey500,
                helperMessage = R.string.sign_up_helper,
                helperIcon = R.drawable.ic_name_text_field_check,
                helperColor = White,
                isButtonValid = true
            )
        }

        nameErrorRegex.matcher(trimmedName).find() -> {
            NameFieldState(
                name = trimmedName,
                lineColor = WarningRed,
                helperMessage = R.string.sign_up_helper_error,
                helperIcon = R.drawable.ic_name_text_field_error,
                helperColor = WarningRed,
                isButtonValid = false
            )
        }

        trimmedName.isEmpty() || trimmedName.isBlank() -> {
            NameFieldState(
                name = trimmedName,
                lineColor = Grey500,
                helperMessage = R.string.sign_up_helper,
                helperIcon = null,
                helperColor = Grey400,
                isButtonValid = false
            )
        }

        isNameOutOfBounds -> {
            NameFieldState(
                name = trimmedName,
                lineColor = WarningRed,
                helperMessage = R.string.sign_up_helper_out,
                helperIcon = R.drawable.ic_name_text_field_error,
                helperColor = WarningRed,
                isButtonValid = false
            )
        }

        else -> {
            NameFieldState(
                name = trimmedName,
                lineColor = TerningMain,
                helperMessage = R.string.sign_up_helper_available,
                helperIcon = R.drawable.ic_name_text_field_check,
                helperColor = TerningMain,
                isButtonValid = true
            )
        }
    }

    onValidationChanged(state.isButtonValid)

    TerningBasicTextField(
        value = state.name,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TerningTheme.typography.detail1,
        textColor = if (value.isNotEmpty()) Black else Grey400,
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
