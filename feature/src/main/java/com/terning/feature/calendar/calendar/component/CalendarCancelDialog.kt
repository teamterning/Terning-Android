package com.terning.feature.calendar.calendar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun CalendarCancelDialog(
    onDismissRequest: () -> Unit,
    onClickScrapCancel: () -> Unit
) {
    TerningBasicDialog(
        onDismissRequest = onDismissRequest
    ) {
        ScrapCancelDialogContent(
            onClickScrapCancel = onClickScrapCancel
        )
    }
}


@Composable
private fun ScrapCancelDialogContent(
    onClickScrapCancel: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_character1
                ),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
                    .background(
                        Grey200,
                        shape = RoundedCornerShape(size = 15.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = TerningMain,
                        shape = RoundedCornerShape(size = 15.dp)
                    ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )

            Text(
                text = stringResource(id = R.string.dialog_content_scrap_cancel_main_title),
                textAlign = TextAlign.Center,
                style = TerningTheme.typography.title4,
                color = Grey500,
                modifier = Modifier.padding(top = 21.dp)
            )
            Text(
                text = stringResource(id = R.string.dialog_content_scrap_cancel_sub_title),
                style = TerningTheme.typography.body5,
                color = Grey350,
                modifier = Modifier.padding(
                    top = 5.dp
                )
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                RoundButton(
                    style = TerningTheme.typography.button3,
                    paddingVertical = 12.dp,
                    cornerRadius = 8.dp,
                    text = R.string.dialog_scrap_cancel_button,
                    onButtonClick = onClickScrapCancel,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TerningBasicDialogPreview() {
    TerningPointTheme {
        TerningBasicDialog(
            onDismissRequest = {},
        ) {
            ScrapCancelDialogContent()
        }
    }
}