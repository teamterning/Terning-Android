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


/**
 * 검색창 텍스트 필드 컴포저블입니다.
 *
 * @param text 텍스트 필드에 표시될 텍스트입니다.
 * @param onValueChange 텍스트 필드의 값이 변경될 때 호출되는 콜백 함수입니다.
 * @param modifier 텍스트 필드에 적용될 Modifier입니다.
 * @param hint 텍스트 필드에 아무것도 입력하지 않았을 때 표시될 힌트 텍스트입니다.
 * @param leftIcon 텍스트 필드의 왼쪽에 표시될 아이콘의 리소스 ID입니다.
 * @param enabled 텍스트 필드가 활성화되어 있는지 여부를 나타냅니다.
 * @param readOnly 텍스트 필드가 읽기 전용인지 여부를 나타냅니다.
 * @param onSearchAction 사용자가 키보드에서 검색을 실행할 때 호출되는 콜백 함수입니다.
 */
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