package com.terning.feature.intern

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R
import com.terning.feature.intern.component.InternInfoRow
import java.text.DecimalFormat

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
            val decimal = DecimalFormat("#,###")

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
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp
                    )
            ) {
                Text(
                    text = "조회수",
                    style = TerningTheme.typography.detail3,
                    color = Grey400
                )
                Text(
                    text = "${decimal.format(100000)}회",
                    style = TerningTheme.typography.button3,
                    color = Grey400,
                )
            }
            Column(
                verticalArrangement = Arrangement.Top,
            ) {
                Row {
                    Text(
                        text = "기업 정보",
                        style = TerningTheme.typography.title4,
                        color = TerningMain,
                        modifier = modifier.padding(end = 32.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                2.dp,
                                Alignment.CenterVertically
                            ),
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(60.dp)
                                    .border(
                                        width = 1.dp,
                                        color = TerningMain,
                                        shape = RoundedCornerShape(size = 30.dp)
                                    )
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.ic_nosearch
                                    ),
                                    modifier = modifier.fillMaxWidth(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    alignment = Alignment.Center
                                )
                            }
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            3.dp,
                            Alignment.Bottom
                        ),
                        horizontalAlignment = Alignment.Start,
                        modifier = modifier
                            .padding(
                                vertical = 6.dp,
                                horizontal = 12.dp
                            )
                    ) {
                        Text(
                            text = "모니모니",
                            style = TerningTheme.typography.title4,
                            color = Black,
                            modifier = modifier.padding(top = 6.dp)
                        )
                        Text(
                            text = "스타트업",
                            style = TerningTheme.typography.body4,
                            color = Grey300,
                        )
                    }
                }
            }
        }
    }
}