package com.terning.core.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.terning.core.designsystem.R
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningMain2
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.designsystem.util.NoRippleTheme

private const val MIN_DIALOG_WIDTH = 300
private const val MIN_TEXT_HEIGHT = 94

/**
 * 앱에서 상태를 업데이트 해야 하는 상황이 있을 때 사용하는 다이얼로그 입니다.
 *
 * @param titleText 다이얼로그의 제목입니다.
 * @param bodyText 다이얼로그의 상세 내용입니다.
 * @param buttonContent 다이얼로그의 내용을 구성하는 버튼 컴포저블 콘텐츠입니다.
 */
@Composable
fun TerningUpdateDialog(
    titleText: String,
    bodyText: String,
    buttonContent: @Composable (Modifier) -> Unit,
) {
    val density = LocalDensity.current
    val shape = RoundedCornerShape(20.dp)
    var dialogSize by remember { mutableStateOf(0.dp) }

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .wrapContentWidth()
                .background(color = White, shape = shape)
                .padding(12.dp),
        ) {
            Text(
                text = titleText,
                style = TerningTheme.typography.title2,
                color = Grey500,
                modifier = Modifier.padding(top = 20.dp)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .defaultMinSize(
                        minHeight = MIN_TEXT_HEIGHT.dp
                    )
                    .onGloballyPositioned {
                        dialogSize = with(density) {
                            max(it.size.width.toDp(), MIN_DIALOG_WIDTH.dp)
                        }
                    }
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = bodyText,
                    style = TerningTheme.typography.body4.copy(
                        textAlign = TextAlign.Center,
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Clip,
                    color = Grey400,
                )
            }

            buttonContent(Modifier.width(dialogSize))
        }
    }
}

/**
 * 업데이트 다이얼로그에 들어가는 버튼입니다.
 *
 * @param text 버튼의 텍스트입니다.
 * @param contentColor 버튼의 배경 색상입니다.
 * @param containerColor 버튼의 텍스트 색상입니다.
 * @param pressedContainerColor 버튼을 누른 상태일 때의 버튼의 배경 색상입니다.
 * @param onClick 버튼을 클릭했을 때 호출되는 함수입니다.
 * @param modifier Modifier 수정자입니다.
 */
@Composable
fun UpdateDialogButton(
    text: String,
    contentColor: Color,
    containerColor: Color,
    pressedContainerColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val backgroundColor = if (isPressed) pressedContainerColor else containerColor
    val shape = RoundedCornerShape(5.dp)

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            contentPadding = PaddingValues(vertical = 12.dp),
            modifier = modifier
                .height(40.dp),
            interactionSource = interactionSource,
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = contentColor,
            ),
            shape = shape,
            onClick = onClick
        ) {
            Text(
                text = text,
                style = TerningTheme.typography.button3,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TerningUpdateDialogPreview() {
    TerningUpdateDialog(
        titleText = "titleText",
        bodyText = "bodyText",
    ) {
        UpdateDialogButton(
            text = stringResource(R.string.button_preview),
            contentColor = White,
            pressedContainerColor = TerningMain2,
            containerColor = TerningMain,
            onClick = { },
            modifier = Modifier,
        )
    }
}