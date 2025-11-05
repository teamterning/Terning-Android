package com.terning.feature.splash.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.dialog.NoticeDialogButton
import com.terning.core.designsystem.component.dialog.TerningNoticeDialog
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningMain2
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.splash.R

@Composable
internal fun TerningServerNoticeDialog(
    onDismissButtonClick: () -> Unit,
    onUpdateButtonClick: () -> Unit,
) {
    TerningNoticeDialog(
        titleText = stringResource(R.string.dialog_title),
        bodyText = stringResource(R.string.dailog_bodytitle),
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(Back)
                .padding(
                    vertical = 18.dp,
                    horizontal = 58.dp
                )
        ) {
            Text(
                text = stringResource(R.string.dialog_server_over_title),
                style = TerningTheme.typography.body6,
                color = Black,
            )
            Text(
                text = stringResource(R.string.dialog_server_over_day),
                style = TerningTheme.typography.detail4,
                color = Grey500
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Row(
            modifier = it,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            NoticeDialogButton(
                text = stringResource(R.string.dialog_dismiss),
                contentColor = Grey350,
                pressedContainerColor = Grey200,
                containerColor = Grey150,
                onClick = onDismissButtonClick,
                modifier = Modifier.weight(1f)
            )
            NoticeDialogButton(
                text = stringResource(R.string.dialog_detail),
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
private fun TerningPatchUpdateDialogPreview() {
    TerningPointTheme {
        TerningServerNoticeDialog(
            onDismissButtonClick = {},
            onUpdateButtonClick = {},
        )
    }
}
