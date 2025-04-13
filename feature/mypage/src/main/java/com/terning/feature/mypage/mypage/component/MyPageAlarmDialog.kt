package com.terning.feature.mypage.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.dialog.TerningUpdateDialog
import com.terning.core.designsystem.component.dialog.UpdateDialogButton
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningMain2
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.mypage.R

@Composable
internal fun MyPageAlarmDialog(
    onLaterClick: () -> Unit,
    onSettingClick: () -> Unit
) {
    TerningUpdateDialog(
        titleText = stringResource(R.string.dialog_title),
        bodyText = stringResource(R.string.dialog_body),
    ) {
        Row(
            modifier = it,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            UpdateDialogButton(
                text = stringResource(R.string.dialog_later),
                contentColor = Grey350,
                pressedContainerColor = Grey200,
                containerColor = Grey150,
                onClick = onLaterClick,
                modifier = Modifier.weight(1f)
            )
            UpdateDialogButton(
                text = stringResource(R.string.dialog_setting),
                contentColor = White,
                pressedContainerColor = TerningMain2,
                containerColor = TerningMain,
                onClick = onSettingClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 500, heightDp = 780)
@Composable
private fun MyPageAlarmDialogPreview() {
    TerningPointTheme {
        MyPageAlarmDialog(
            onLaterClick = {},
            onSettingClick = {}
        )
    }
}