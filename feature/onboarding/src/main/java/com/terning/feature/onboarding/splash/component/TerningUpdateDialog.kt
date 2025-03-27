package com.terning.feature.onboarding.splash.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningMain2
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.designsystem.util.NoRippleTheme
import com.terning.feature.onboarding.R.string.update_dialog_major_button_update
import com.terning.feature.onboarding.R.string.update_dialog_patch_button_dismiss
import com.terning.feature.onboarding.R.string.update_dialog_patch_button_update

private const val MIN_DIALOG_WIDTH = 300
private const val MIN_TEXT_HEIGHT = 94

/**
 * 중~대규모 업데이트의 경우 사용하는 팝업 다이얼로그입니다.
 * 중~대규모 업데이트란 minor 혹은 major 버전을 올린 경우를 의미합니다.
 */
@Composable
internal fun TerningMajorUpdateDialog(
    titleText: String,
    bodyText: String,
    onUpdateButtonClick: () -> Unit,
) {
    TerningUpdateDialog(
        titleText = titleText,
        bodyText = bodyText,
    ) {
        UpdateDialogButton(
            text = stringResource(update_dialog_major_button_update),
            contentColor = White,
            pressedContainerColor = TerningMain2,
            containerColor = TerningMain,
            onClick = onUpdateButtonClick,
            modifier = it,
        )
    }
}

/**
 * 소규모 업데이트의 경우 사용하는 팝업 다이얼로그입니다.
 * 소규모 업데이트는 Patch 버전을 올린 경우를 의미합니다.
 */
@Composable
internal fun TerningPatchUpdateDialog(
    titleText: String,
    bodyText: String,
    onDismissButtonClick: () -> Unit,
    onUpdateButtonClick: () -> Unit,
) {
    TerningUpdateDialog(
        titleText = titleText,
        bodyText = bodyText,
    ) {
        Row(
            modifier = it,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            UpdateDialogButton(
                text = stringResource(update_dialog_patch_button_dismiss),
                contentColor = Grey350,
                pressedContainerColor = Grey200,
                containerColor = Grey150,
                onClick = onDismissButtonClick,
                modifier = Modifier.weight(1f)
            )

            UpdateDialogButton(
                text = stringResource(update_dialog_patch_button_update),
                contentColor = White,
                pressedContainerColor = TerningMain2,
                containerColor = TerningMain,
                onClick = onUpdateButtonClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun UpdateDialogButton(
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

@Composable
private fun TerningUpdateDialog(
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
                        minHeight = with(density) {
                            MIN_TEXT_HEIGHT.toDp()
                        }
                    )
                    .onGloballyPositioned {
                        dialogSize = with(density) {
                            max(it.size.width.toDp(), MIN_DIALOG_WIDTH.toDp())
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

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun MajorUpdateDialogPreview() {
    TerningPointTheme {
        TerningMajorUpdateDialog(
            titleText = "v n.n.n 업데이트 안내",
            bodyText = "업데이트 내용 작성",
            onUpdateButtonClick = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun TerningPatchUpdateDialogPreview() {
    TerningPointTheme {
        TerningPatchUpdateDialog(
            titleText = "새로운 버전 업데이트 안내",
            bodyText = "최적의 서비스 사용 환경을 위해 최신 버전으로 업데이트 해주세요!",
            onDismissButtonClick = {},
            onUpdateButtonClick = {},
        )
    }
}