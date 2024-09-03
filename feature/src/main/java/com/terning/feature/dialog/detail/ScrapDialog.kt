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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.Grey100
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.type.ColorType
import com.terning.feature.R
import com.terning.feature.dialog.detail.component.ColorPalette
import com.terning.feature.intern.component.InternInfoRow


@Composable
fun ScrapDialog(
    title: String,
    scrapColor: Color,
    deadline: String,
    startYear: Int,
    startMonth: Int,
    workingPeriod: String,
    scrapId: Long,
    internshipAnnouncementId: Long,
    companyImage: String,
    isScrapped: Boolean,
    onDismissRequest: () -> Unit,
    onClickChangeColor: () -> Unit,
    onClickNavigateButton: (Long) -> Unit,
    viewModel: ScrapDialogViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is ScrapDialogSideEffect.ShowToast -> {}
                    is ScrapDialogSideEffect.DismissDialog -> onDismissRequest()
                    is ScrapDialogSideEffect.ChangedColor -> onClickChangeColor()
                    is ScrapDialogSideEffect.NavigateToDetail -> onClickNavigateButton(
                        internshipAnnouncementId
                    )
                    is ScrapDialogSideEffect.ScrappedAnnouncement -> {}
                }
            }
    }

    TerningBasicDialog(
        onDismissRequest = onDismissRequest
    ) {
        ScrapDialogScreen(
            title = title,
            scrapColor = scrapColor,
            deadline = deadline,
            startYear = startYear,
            startMonth = startMonth,
            workingPeriod = workingPeriod,
            isScrapped = isScrapped,
            companyImage = companyImage,
            onClickColorChangeButton = { newColor ->
                onClickChangeColor()
                viewModel.patchScrap(scrapId = scrapId, color = newColor)
            },
            onClickNavigateButton = viewModel::navigateToDetail,
            onClickScrapButton = { viewModel.postScrap(internshipAnnouncementId, scrapColor) }
        )
    }
}


@Composable
private fun ScrapDialogScreen(
    title: String,
    scrapColor: Color,
    deadline: String,
    startYear: Int,
    startMonth: Int,
    workingPeriod: String,
    isScrapped: Boolean,
    companyImage: String,
    onClickNavigateButton: () -> Unit,
    onClickColorChangeButton: (Color) -> Unit,
    onClickScrapButton: () -> Unit
) {
    var selectedColor by remember { mutableStateOf(scrapColor) }
    var selectedColorType by remember { mutableStateOf(ColorType.findColorType(selectedColor)?:ColorType.RED) }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 32.dp, bottom = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 11.dp),
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
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = TerningTheme.typography.title4,
                color = Grey500,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = stringResource(id = R.string.dialog_scrap_mine),
                style = TerningTheme.typography.body5,
                color = Grey350,
                modifier = Modifier.padding(
                    top = 4.dp
                )
            )
            Spacer(modifier = Modifier.height(26.dp))
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
                        style = TerningTheme.typography.body5,
                        color = Grey400,
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 12.dp,
                            bottom = 23.dp,
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    ColorPalette(
                        initialColor = selectedColorType,
                        onColorSelected = { newColor ->
                            selectedColor = newColor.main
                            selectedColorType = newColor
                        }
                    )
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Grey200,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )
                Column(
                    modifier = Modifier.padding(bottom = 29.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        5.dp,
                        Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.Start,
                ) {
                    InternInfoRow(
                        title = stringResource(id = com.terning.feature.R.string.intern_info_d_day),
                        value = deadline
                    )
                    InternInfoRow(
                        title = stringResource(id = com.terning.feature.R.string.intern_info_working),
                        value = workingPeriod
                    )
                    InternInfoRow(
                        title = stringResource(id = com.terning.feature.R.string.intern_info_start_date),
                        value = "${startYear}년 ${startMonth}월"
                    )
                }
            }

            if(isScrapped) {
                DetailScrapButton(
                    onClickNavigateButton = onClickNavigateButton,
                    onClickColorChangeButton = { onClickColorChangeButton(selectedColor) }
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
    modifier: Modifier = Modifier
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
    onClickNavigateButton: () -> Unit,
    onClickColorChangeButton: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier
    ){
        RoundButton(
            style = TerningTheme.typography.button3,
            paddingVertical = 12.dp,
            cornerRadius = 8.dp,
            text = R.string.dialog_content_calendar_color_change,
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
            title = "터닝 하반기 채용",
            scrapColor = Color.Red,
            deadline = "2024/09/07",
            startYear = 2024,
            startMonth = 11,
            workingPeriod = "2개월",
            companyImage = "",
            isScrapped = false,
            onClickNavigateButton = {},
            onClickColorChangeButton = {},
            onClickScrapButton = {}
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
            onClickColorChangeButton = {}
        )
    }
}