package com.terning.feature.intern.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.terning.core.designsystem.component.item.ColorPalette
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.feature.intern.InternViewModel


@Composable
fun ScrapDialogContent(
    isScrapped: MutableState<Boolean> = mutableStateOf(false),
    internInfoList: List<Pair<String, String>>,
    viewModel: InternViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
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
                text = "[한양대학교 컬렉티브임팩트센터] /코이카 영프로페셔널(YP) 모집합니다",
                textAlign = TextAlign.Center,
                style = TerningTheme.typography.title4,
                color = Grey500,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = stringResource(id = R.string.dialog_content_scrap_sub_title),
                style = TerningTheme.typography.body5,
                color = Grey350,
                modifier = Modifier.padding(
                    top = 4.dp,
                    bottom = 13.dp
                )
            )
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
                            viewModel.updateColorChange(!state.isColorChange)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (state.isColorChange) R.drawable.ic_up_22
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
                if (state.isColorChange) {
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
                            onColorSelected = {
                                viewModel.updateSelectColor(it)
                            },
                        )
                    }
                } else {
                    Text(
                        text = stringResource(id = R.string.intern_item_d_day),
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
                        internInfoList.forEach { (title, value) ->
                            InternInfoRow(title, value)
                        }
                    }
                }
            }
            RoundButton(
                style = TerningTheme.typography.button3,
                paddingVertical = 12.dp,
                cornerRadius = 8.dp,
                text = if (isScrapped.value) {
                    if (state.isColorChange)
                        R.string.dialog_content_color_button
                    else R.string.dialog_scrap_button
                } else {
                    R.string.dialog_scrap_button
                },
                onButtonClick = {
                    viewModel.updateScrapped(!state.isScrapped)
                    viewModel.updateScrapDialogVisible(false)
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}