package com.terning.feature.splash.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.dialog.NoticeDialogButton
import com.terning.core.designsystem.component.dialog.TerningNoticeDialog
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningMain2
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.splash.R.string.update_dialog_major_button_update
import com.terning.feature.splash.R.string.update_dialog_patch_button_dismiss
import com.terning.feature.splash.R.string.update_dialog_patch_button_update

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
    TerningNoticeDialog(
        titleText = titleText,
        bodyText = bodyText,
    ) {
        NoticeDialogButton(
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
    TerningNoticeDialog(
        titleText = titleText,
        bodyText = bodyText,
    ) {
        Row(
            modifier = it,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            NoticeDialogButton(
                text = stringResource(update_dialog_patch_button_dismiss),
                contentColor = Grey350,
                pressedContainerColor = Grey200,
                containerColor = Grey150,
                onClick = onDismissButtonClick,
                modifier = Modifier.weight(1f)
            )

            NoticeDialogButton(
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