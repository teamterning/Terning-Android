package com.terning.feature.home.home.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.R
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.component.item.ColorPalette
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange1
import com.terning.core.designsystem.theme.CalOrange2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.feature.home.home.HomeViewModel
import com.terning.feature.intern.component.InternInfoRow


@Composable
fun HomeTodayInternContent(
    internInfoList: List<Pair<String, String>>,
    navigateTo: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    homeTodayInternModel: HomeTodayInternModel,
    announcementId: Long,
) {
    val state by viewModel.homeScrapViewState.collectAsStateWithLifecycle()

    val colorList = listOf(
        CalRed,
        CalOrange1,
        CalOrange2,
        CalYellow,
        CalGreen1,
        CalGreen2,
        CalBlue1,
        CalBlue2,
        CalPurple,
        CalPink
    )

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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(homeTodayInternModel.companyImage)
                    .build(),
                contentDescription = stringResource(R.string.image_content_descriptin),
                modifier = Modifier
                    .size(80.dp)
                    .border(
                        width = 1.dp,
                        color = TerningMain,
                        shape = RoundedCornerShape(size = 15.dp)
                    )
                    .clip(RoundedCornerShape(size = 15.dp)),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )

            Text(
                text = homeTodayInternModel.title,
                textAlign = TextAlign.Center,
                style = TerningTheme.typography.title4,
                color = Grey500,
                modifier = Modifier.padding(top = 20.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(
                    id = R.string.dialog_today_deadline
                ),
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
                            color = state.selectedColor,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .noRippleClickable {
                            viewModel.updatePaletteOpen(!state.isPaletteOpen)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_content_color_button),
                        style = TerningTheme.typography.body5,
                        color = White,
                        modifier = Modifier.padding(
                            start = 13.dp,
                            top = 5.dp,
                            bottom = 5.dp
                        )
                    )
                    Icon(
                        painter = painterResource(
                            id = if (state.isPaletteOpen) R.drawable.ic_up_22
                            else R.drawable.ic_down_22
                        ),
                        contentDescription = stringResource(
                            id = R.string.dialog_content_color_button
                        ),
                        tint = White,
                        modifier = Modifier.padding(
                            end = 7.dp
                        )
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
                if (state.isPaletteOpen) {
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
                                viewModel.updateSelectColor(newColor)
                            }
                        )
                    }
                } else {
                    Text(
                        text = homeTodayInternModel.dDay,
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
                        internInfoList.forEach {
                            InternInfoRow(
                                title = it.first,
                                value = it.second
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                val selectedColorIndex =
                    colorList.indexOf(state.selectedColor).takeIf { it >= 0 } ?: 0

                RoundButton(
                    style = TerningTheme.typography.button3,
                    paddingVertical = 12.dp,
                    cornerRadius = 8.dp,
                    text = R.string.dialog_scrap_move_to_intern,
                    onButtonClick = {
                        if (state.isPaletteOpen) {
                            viewModel.updatePaletteOpen(false)
                            viewModel.postScrap(announcementId, selectedColorIndex)
                            viewModel.updateColorChange(false)
                            viewModel.updateScrapDialogVisible(false)
                        } else {
                            if (state.isColorChange) {
                                viewModel.postScrap(announcementId, selectedColorIndex)
                                viewModel.updateColorChange(false)
                            } else {
                                viewModel.postScrap(announcementId, 0)
                            }
                            viewModel.updateScrapDialogVisible(false)
                        }
                        navigateTo()
                    },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}