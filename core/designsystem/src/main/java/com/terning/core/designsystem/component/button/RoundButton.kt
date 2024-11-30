package com.terning.core.designsystem.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.R
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme

/**
 * 모서리의 둥근 부분을 설정할 수 있는 버튼입니다.
 *
 * @param style 버튼의 텍스트에 적용할 텍스트 스타일입니다.
 * @param paddingVertical 버튼 내부 콘텐츠의 수직 패딩 값입니다.
 * @param cornerRadius 버튼의 모서리 반경을 설정하는 Dp 값입니다.
 * @param text 버튼에 표시될 문자열의 리소스 ID입니다.
 * @param onButtonClick 버튼 클릭 시 호출될 콜백 함수입니다.
 * @param modifier 버튼에 적용할 Modifier입니다.
 * @param isEnabled 버튼 활성화 여부를 설정할 수 있습니다.
 */
@Composable
fun RoundButton(
    style: TextStyle,
    paddingVertical: Dp,
    cornerRadius: Dp,
    @StringRes text: Int,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    TerningBasicButton(
        modifier = modifier,
        style = style,
        paddingVertical = paddingVertical,
        shape = RoundedCornerShape(cornerRadius),
        text = text,
        onButtonClick = onButtonClick,
        isEnabled = isEnabled,
    )
}

@Preview(showBackground = true)
@Composable
fun RoundButtonPreview() {
    TerningPointTheme {
        RoundButton(
            style = TerningTheme.typography.button0,
            text = R.string.button_preview,
            paddingVertical = 19.dp,
            cornerRadius = 10.dp,
            onButtonClick = {}
        )
    }
}