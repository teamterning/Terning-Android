package com.terning.core.designsystem.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.util.NoRippleTheme

/**
 * 취소 기능을 하는 버튼입니다.
 *
 * @param style  버튼의 텍스트에 적용할 텍스트 스타일입니다.
 * @param paddingVertical 버튼 내부 콘텐츠의 수직 패딩 값입니다.
 * @param text 버튼에 표시될 문자열의 리소스 ID입니다.
 * @param onButtonClick 버튼 클릭 시 호출될 콜백 함수입니다.
 * @param modifier 버튼에 적용할 Modifier입니다.
 * @param isEnabled 버튼 활성화 여부를 설정할 수 있습니다.
 * @param cornerRadius 버튼의 모서리 반경을 설정하는 Dp 값입니다.
 */
@Composable
fun DeleteRoundButton(
    style: TextStyle,
    paddingVertical: Dp,
    @StringRes text: Int,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    cornerRadius: Dp
) {
    val interactionSource = remember { MutableInteractionSource() }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            contentPadding = PaddingValues(paddingVertical),
            modifier = modifier.fillMaxWidth(),
            interactionSource = interactionSource,
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = White,
                contentColor = Grey400,
            ),
            shape = RoundedCornerShape(cornerRadius),
            onClick = { onButtonClick() },
            border = BorderStroke(width = 1.dp, color = Grey400)
        ) {
            Text(
                text = stringResource(id = text),
                style = style,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteRoundButtonPreview() {
    TerningPointTheme {
        DeleteRoundButton(
            style = TextStyle(),
            paddingVertical = 15.dp,
            text = R.string.button_preview,
            onButtonClick = {},
            cornerRadius = 10.dp
        )
    }
}