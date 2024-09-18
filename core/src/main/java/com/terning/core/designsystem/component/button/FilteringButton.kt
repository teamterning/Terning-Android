package com.terning.core.designsystem.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey375
import com.terning.core.designsystem.theme.Grey50
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningSub5
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.util.NoRippleTheme

/**
 * 온보딩 과정에서 필터링을 설정할 때 사용되는 버튼입니다.
 *
 * 버튼 클릭을 클릭했을 때, 버튼의 배경 색과 텍스트 색상이 바뀝니다.
 *
 * @param isSelected 버튼이 선택됐는지 여부를 나타냅니다.
 * @param text 버튼에 표시될 문자열의 리소스 ID입니다.
 * @param cornerRadius 버튼의 모서리 반경을 설정하는 Dp 값입니다.
 * @param paddingVertical 버튼 내부 콘텐츠의 수직 패딩 값입니다.
 * @param onButtonClick 버튼 클릭 시 호출될 콜백 함수입니다.
 * @param isEnabled 버튼 활성화 여부를 설정할 수 있습니다.
 * @param modifier 버튼에 적용할 Modifier입니다.
 */
@Composable
fun FilteringButton(
    isSelected: Boolean,
    @StringRes text: Int,
    cornerRadius: Dp,
    paddingVertical: Dp,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val backgroundColor = when {
        !isSelected && isPressed -> Grey50
        isSelected && isPressed -> TerningSub5
        else -> White
    }
    val textColor = when {
        !isSelected && !isPressed -> Grey375
        !isSelected && isPressed -> Grey375
        else -> TerningMain
    }
    val borderColor = when {
        !isSelected && !isPressed -> Grey150
        !isSelected && isPressed -> Grey200
        else -> TerningMain
    }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            contentPadding = PaddingValues(paddingVertical),
            modifier = modifier.fillMaxWidth(),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = textColor,
            ),
            border = BorderStroke(
                width = 1.dp,
                color = borderColor
            ),
            shape = RoundedCornerShape(cornerRadius),
            onClick = onButtonClick
        ) {
            Text(
                text = stringResource(id = text),
                style = TerningTheme.typography.button3_a,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilteringButtonPreview() {
    TerningPointTheme {
        Column {
            FilteringButton(
                isSelected = false,
                text = R.string.button_preview,
                cornerRadius = 15.dp,
                paddingVertical = 10.dp,
                onButtonClick = {}
            )
            FilteringButton(
                isSelected = true,
                text = R.string.button_preview,
                cornerRadius = 15.dp,
                paddingVertical = 10.dp,
                onButtonClick = {}
            )
        }
    }
}