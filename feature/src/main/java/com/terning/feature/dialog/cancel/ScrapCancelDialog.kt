package com.terning.feature.dialog.cancel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.R
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.component.item.TerningLottieAnimation
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun ScrapCancelDialog(
    scrapId: Long,
    onDismissRequest: (Boolean) -> Unit,
    viewModel: ScrapCancelViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect{ sideEffect ->
                when(sideEffect){
                    is ScrapCancelSideEffect.DismissDialog -> {
                        onDismissRequest(true)
                    }
                    is ScrapCancelSideEffect.ShowToast -> {}
                }
            }
    }


    TerningBasicDialog(
        onDismissRequest = { onDismissRequest(false) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TerningLottieAnimation(
                    jsonString = "terning_scrap_cancel.json",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )

                Text(
                    text = stringResource(id = R.string.dialog_content_scrap_cancel_main_title),
                    textAlign = TextAlign.Center,
                    style = TerningTheme.typography.title4,
                    color = Grey500,
                )
                Text(
                    text = stringResource(id = R.string.dialog_content_scrap_cancel_sub_title),
                    style = TerningTheme.typography.body5,
                    color = Grey350,
                    modifier = Modifier.padding(
                        top = 5.dp
                    )
                )
                RoundButton(
                    style = TerningTheme.typography.button3,
                    paddingVertical = 12.dp,
                    cornerRadius = 8.dp,
                    text = R.string.dialog_scrap_cancel_button,
                    onButtonClick = { viewModel.deleteScrap(scrapId) },
                    modifier = Modifier.padding(bottom = 8.dp, top = 40.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TerningBasicDialogPreview() {
    TerningPointTheme {
        ScrapCancelDialog(
            scrapId = 1,
            onDismissRequest = {}
        )
    }
}