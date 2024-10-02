package com.terning.feature.dialog.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.theme.Grey100
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.getFixHeightByMaxLine
import com.terning.core.extension.toast
import com.terning.core.type.ColorType
import com.terning.feature.R
import com.terning.feature.dialog.detail.component.ColorPalette
import com.terning.feature.dialog.detail.component.ScrapColorChangeButton
import com.terning.feature.dialog.detail.component.ScrapInfoRow


@Composable
fun ScrapDialog(
    title: String,
    scrapColor: Color,
    deadline: String,
    startYearMonth: String,
    workingPeriod: String,
    internshipAnnouncementId: Long,
    companyImage: String,
    isScrapped: Boolean,
    onDismissRequest: (Boolean) -> Unit = {},
    onScrapAnnouncement: () -> Unit = {},
    onClickChangeColor: () -> Unit = {},
    onClickNavigateButton: (Long) -> Unit = {},
    viewModel: ScrapDialogViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is ScrapDialogSideEffect.ShowToast -> {
                        context.toast(sideEffect.message)
                    }

                    is ScrapDialogSideEffect.DismissDialog -> {
                        viewModel.initUiState()
                        onDismissRequest(false)
                    }

                    is ScrapDialogSideEffect.PatchedScrap -> {
                        onClickChangeColor()
                        onDismissRequest(false)
                        context.toast(R.string.dialog_content_calendar_color_change_complete)
                    }

                    is ScrapDialogSideEffect.NavigateToDetail -> onClickNavigateButton(
                        internshipAnnouncementId
                    )

                    is ScrapDialogSideEffect.ScrappedAnnouncement -> {
                        onScrapAnnouncement()
                        onDismissRequest(true)
                    }
                }
            }
    }

    LaunchedEffect(true) {
        val colorType = ColorType.findColorType(scrapColor).takeIf { it != null }
        colorType?.let { viewModel.setInitialAndSelectedColorType(it) }
    }

    TerningBasicDialog(
        onDismissRequest = viewModel::navigateUp
    ) {
        ScrapDialogScreen(
            title = title,
            deadline = deadline,
            startYearMonth = startYearMonth,
            workingPeriod = workingPeriod,
            isScrapped = isScrapped,
            companyImage = companyImage,
            selectedColorType = uiState.selectedColorType,
            isColorChanged = uiState.isColorChanged,
            onClickColorButton = viewModel::changeSelectedColor,
            onClickColorChangeButton = {
                if (uiState.isColorChanged)
                    viewModel.patchScrap(
                        scrapId = internshipAnnouncementId,
                        color = uiState.selectedColorType
                    )
            },
            onClickNavigateButton = viewModel::navigateToDetail,
            onClickScrapButton = {
                viewModel.postScrap(internshipAnnouncementId, uiState.selectedColorType)
            }
        )
    }
}


@Composable
private fun ScrapDialogScreen(
    title: String,
    deadline: String,
    startYearMonth: String,
    workingPeriod: String,
    isScrapped: Boolean,
    companyImage: String,
    selectedColorType: ColorType,
    isColorChanged: Boolean,
    onClickColorButton: (ColorType) -> Unit,
    onClickNavigateButton: () -> Unit,
    onClickColorChangeButton: () -> Unit,
    onClickScrapButton: () -> Unit,
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 32.dp, bottom = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(companyImage)
                    .build(),
                contentDescription = title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(80.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(15.dp))
                    .border(
                        width = 1.dp,
                        color = TerningMain,
                        shape = RoundedCornerShape(size = 15.dp)
                    )
            )

            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .height(TerningTheme.typography.title4.getFixHeightByMaxLine(3))
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = TerningTheme.typography.title4,
                    color = Grey500,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Grey100,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_content_color_button),
                        style = TerningTheme.typography.body6,
                        color = Grey400,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                ColorPalette(
                    selectedColor = selectedColorType,
                    onColorSelected = onClickColorButton
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Grey200,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 13.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    5.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
            ) {
                ScrapInfoRow(
                    title = stringResource(id = R.string.intern_info_d_day),
                    value = deadline
                )
                ScrapInfoRow(
                    title = stringResource(id = R.string.intern_info_working),
                    value = workingPeriod
                )
                ScrapInfoRow(
                    title = stringResource(id = R.string.intern_info_start_date),
                    value = startYearMonth
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (isScrapped) {
                DetailScrapButton(
                    isColorChanged = isColorChanged,
                    onClickNavigateButton = onClickNavigateButton,
                    onClickColorChangeButton = onClickColorChangeButton
                )
            } else {
                NewScrapButton(onClickScrapButton = onClickScrapButton)
            }
        }
    }
}

@Composable
private fun NewScrapButton(
    onClickScrapButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RoundButton(
        style = TerningTheme.typography.button3,
        paddingVertical = 12.dp,
        cornerRadius = 8.dp,
        text = R.string.dialog_scrap_button,
        onButtonClick = onClickScrapButton,
        modifier = modifier
    )
}

@Composable
private fun DetailScrapButton(
    isColorChanged: Boolean,
    onClickNavigateButton: () -> Unit,
    onClickColorChangeButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        ScrapColorChangeButton(
            isColorChanged = isColorChanged,
            paddingVertical = 12.dp,
            cornerRadius = 8.dp,
            onButtonClick = onClickColorChangeButton,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        RoundButton(
            style = TerningTheme.typography.button3,
            paddingVertical = 12.dp,
            cornerRadius = 8.dp,
            text = R.string.dialog_scrap_move_to_intern,
            onButtonClick = onClickNavigateButton,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScrapDialogPreview() {
    TerningPointTheme {
        ScrapDialogScreen(
            title = "터닝 하반기 채용터닝 하반기 채용터닝 하반기 채용터닝터닝 하반기 채용터닝터닝 하반기 채용터닝터닝 하반기 채용터닝터닝 하반기 채용터닝터닝 하반기 채용터닝 하반기 채용터닝 하반기 채용터닝 하반기 채용터닝 하반기 채용터닝 하반기 채용터닝 하반기 채용",
            deadline = "2024/09/07",
            startYearMonth = "2024년 11월",
            workingPeriod = "2개월",
            companyImage = "",
            isScrapped = false,
            onClickNavigateButton = {},
            onClickColorChangeButton = {},
            onClickScrapButton = {},
            onClickColorButton = {},
            isColorChanged = false,
            selectedColorType = ColorType.RED
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewScrapButtonPreview() {
    TerningPointTheme {
        NewScrapButton(
            onClickScrapButton = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScrapButtonPreview() {
    TerningPointTheme {
        DetailScrapButton(
            onClickNavigateButton = {},
            onClickColorChangeButton = {},
            isColorChanged = false,
        )
    }
}