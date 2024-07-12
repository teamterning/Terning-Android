package com.terning.core.designsystem.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable


@Composable
fun DialogContent(
    onDismissRequest: () -> Unit,
    isScrapped: Boolean,
) {
    var isColorChange by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(30.dp)
            .background(
                color = White,
                shape = RoundedCornerShape(20.dp)
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { onDismissRequest() }, modifier = Modifier.padding(6.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dialog_x_32),
                    contentDescription = null,
                    tint = Grey300
                )
            }
        }
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
                        .width(60.dp)
                        .height(60.dp)
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
                    text = "공고를 캘린더에 스크랩하시겠어요?",
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
                                color = CalGreen2,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .noRippleClickable {
                                isColorChange = !isColorChange
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isColorChange) R.drawable.ic_up_22
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
                    if (isColorChange) {
                        Text(text = "컬러 팔레트")
                    } else {
                        Text(
                            text = "D-3",
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
                            listOf(
                                "서류 마감" to "2024년 7월 23일",
                                "근무 기간" to "2개월",
                                "근무 시작" to "2024년 8월"
                            ).forEach { (title, value) ->
                                InternInfoRow(title, value)
                            }
                        }
                    }
                }
                RoundButton(
                    style = TerningTheme.typography.button3,
                    paddingVertical = 12.dp,
                    cornerRadius = 8.dp,
                    text = if (isScrapped) {
                        if (isColorChange)
                            R.string.dialog_content_color_button
                        else R.string.dialog_scrap_button
                    } else {
                        R.string.dialog_scrap_button
                    },
                    onButtonClick = { /*TODO*/ },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

// TODO 삭제
@Composable
fun InternInfoRow(title: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = title,
            style = TerningTheme.typography.body2,
            color = Grey350,
        )
        Text(
            text = value,
            style = TerningTheme.typography.body3,
            color = Grey500,
        )
    }
}
