package com.terning.feature.intern

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R

@Composable
fun InternRoute(
    navController: NavHostController,
) {
    InternScreen(
        navController = navController
    )
}

@Composable
fun InternScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(
                title = "공고 정보",
                modifier = Modifier.customShadow(
                    color = Grey200,
                    offsetY = 2.dp
                ),
                onBackButtonClick = {},
                listOf(
                    {},
                    {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_intern_share_22),
                                contentDescription = stringResource(
                                    id = R.string.intern_share_icon
                                ),
                                modifier = modifier
                                    .noRippleClickable { }
                            )
                        }
                    },
                    {
                        Spacer(modifier = modifier.padding(end = 8.dp))
                    }
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Text(
                text = "D-3",
                style = TerningTheme.typography.body0,
                color = TerningMain
            )
            Text(
                text = "[NAVER Cloud] 의료 특화 초거대 언어모델 학습 데이터 구축 및 안전성 평가 업무 (체험형 인턴)",
                style = TerningTheme.typography.title2,
                color = Black,
                modifier = modifier.padding(
                    top = 8.dp,
                    bottom = 16.dp
                )
            )
            Column(
                modifier = modifier
                    .border(
                        width = 1.dp,
                        color = TerningMain,
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 17.dp, bottom = 15.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                val internInfo = listOf(
                    "서류 마감" to "2024년 7월 23일",
                    "근무 기간" to "2개월",
                    "근무 시작" to "2024년 8월"
                )
                internInfo.forEach { (title, value) ->
                    InternInfoRow(title, value)
                }
            }
            Text(text = "조회수 3,")
        }
    }
}

@Composable
fun InternInfoRow(title: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = title,
            style = TerningTheme.typography.body2,
            color = Black,
        )
        Text(
            text = value,
            style = TerningTheme.typography.body3,
            color = Grey500,
        )
    }
}