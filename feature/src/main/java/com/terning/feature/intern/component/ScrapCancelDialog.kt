package com.terning.feature.intern.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.R
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.intern.InternViewModel


@Composable
fun ScrapCancelDialogContent(
    viewModel: InternViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        width = 1.dp,
                        color = TerningMain,
                        shape = RoundedCornerShape(size = 15.dp)
                    )
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_character1
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Grey200,
                            shape = RoundedCornerShape(size = 15.dp)
                        ),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            }
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
                    top = 5.dp,
                    bottom = 41.dp
                )
            )
            RoundButton(
                style = TerningTheme.typography.button3,
                paddingVertical = 12.dp,
                cornerRadius = 8.dp,
                text = R.string.dialog_scrap_cancel_button,
                onButtonClick = {
                    viewModel.updateScrapDialogVisible(false)
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}