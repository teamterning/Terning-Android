package com.terning.feature.calendar.calendar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.terning.core.R
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.component.item.ColorPalette
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.response.CalendarScrapDetailModel
import com.terning.feature.intern.component.InternInfoRow
import com.terning.feature.intern.model.InternViewState

@Composable
fun CalendarDetailDialog(
    onDismissRequest: () -> Unit,
    onClickChangeColorButton: (Color) -> Unit,
    onClickNavigateButton: (Long) -> Unit,
) {
    TerningBasicDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = true,
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
        )
    ) {
        InternDialogContent(
            scrapDetailModel = CalendarScrapDetailModel(
                scrapId = 1,
                internshipAnnouncementId = 1,
                title = "안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕",
                dDay = "D-8",
                workingPeriod = "9개월",
                color = "0xf3d1e3",
                companyImage = "",
                startYear = 2024,
                startMonth = 8,
                deadLine = "2024-07-13",
                isScrapped = true
            ),
            state = InternViewState(),
            onClickChangeColorButton = onClickChangeColorButton,
            onClickNavigateButton = onClickNavigateButton
        )
    }
}


@Composable
private fun InternDialogContent(
    scrapDetailModel: CalendarScrapDetailModel,
    state: InternViewState,
    onClickChangeColorButton: (Color) -> Unit,
    onClickNavigateButton: (Long) -> Unit
) {
    var isPaletteOpen by remember { mutableStateOf(false) }
    var selectedColor by remember {mutableStateOf(Color.Red)}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 11.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_character1
                ),
                modifier = Modifier
                    .size(80.dp)
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
                text = scrapDetailModel.title,
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
                Row(
                    modifier = Modifier
                        .background(
                            color = if (state.selectedColor != CalRed) CalBlue2 else CalGreen2,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .noRippleClickable {
                            isPaletteOpen = !isPaletteOpen
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isPaletteOpen) R.drawable.ic_up_22
                            else R.drawable.ic_down_22
                        ),
                        contentDescription = stringResource(
                            id = R.string.dialog_content_color_button
                        ),
                        tint = White,
                        modifier = Modifier.padding(
                            start = 7.dp,
                            top = 2.dp,
                            bottom = 2.dp
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.dialog_content_color_button),
                        style = TerningTheme.typography.body5,
                        color = White,
                        modifier = Modifier.padding(end = 13.dp)
                    )
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Grey200,
                    modifier = Modifier.padding(
                        top = 11.dp,
                        bottom = 8.dp
                    )
                )
                if (isPaletteOpen) {
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
                            initialColor = CalRed,
                            onColorSelected = { newColor ->
                                selectedColor = newColor
                            }
                        )
                    }
                } else {
                    Text(
                        text = scrapDetailModel.dDay,
                        style = TerningTheme.typography.body5,
                        color = TerningMain,
                        modifier = Modifier.padding(bottom = 9.dp)
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
                            value = scrapDetailModel.title
                        )
                        InternInfoRow(
                            title = stringResource(id = com.terning.feature.R.string.intern_info_working),
                            value = scrapDetailModel.workingPeriod
                        )
                        InternInfoRow(
                            title = stringResource(id = com.terning.feature.R.string.intern_info_start_date),
                            value = "${scrapDetailModel.startYear}년 ${scrapDetailModel.startMonth}월"
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                RoundButton(
                    style = TerningTheme.typography.button3,
                    paddingVertical = 12.dp,
                    cornerRadius = 8.dp,
                    text = if (isPaletteOpen) R.string.dialog_content_calendar_color_change
                    else R.string.dialog_scrap_move_to_intern,
                    onButtonClick = {
                        if (isPaletteOpen) {
                            onClickChangeColorButton(selectedColor)
                            isPaletteOpen = false
                        } else {
                            onClickNavigateButton(scrapDetailModel.internshipAnnouncementId)
                        }
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InternDialogContentPreview() {
    TerningPointTheme {
        CalendarDetailDialog(
            onDismissRequest = {},
            onClickChangeColorButton = {},
            onClickNavigateButton = {}
        )
    }
}
