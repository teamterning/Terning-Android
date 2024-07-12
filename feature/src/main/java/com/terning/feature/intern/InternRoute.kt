package com.terning.feature.intern

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
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
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R
import com.terning.feature.intern.component.InternBottomBar
import com.terning.feature.intern.component.InternCompanyInfo
import com.terning.feature.intern.component.InternInfoRow
import com.terning.feature.intern.component.InternPageTitle
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
                                    .noRippleClickable { },
                                tint = Grey300
                            )
                        }
                    },
                    {
                        Spacer(modifier = modifier.padding(end = 8.dp))
                    }
                )
            )
        },
        bottomBar = {
            InternBottomBar(modifier = modifier)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
        ) {
            item {
                val decimal = DecimalFormat("#,###")

                Column(
                    modifier = modifier.padding(
                        top = 24.dp,
                        start = 24.dp,
                        end = 24.dp
                    )
                ) {
                    Row(
                        modifier = modifier
                            .border(
                                width = 1.dp,
                                color = TerningMain,
                                shape = RoundedCornerShape(size = 12.dp)
                            ),
                        horizontalArrangement = Arrangement.spacedBy(
                            0.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "D-3",
                            style = TerningTheme.typography.body0,
                            color = TerningMain,
                            modifier = Modifier.padding(
                                start = 14.dp, end = 15.dp
                            )
                        )
                    }

                    Text(
                        text = "[NAVER Cloud] 의료 특화 초거대 언어모델 학습 데이터 구축 및 안전성 평가 업무 (체험형 인턴)",
                        style = TerningTheme.typography.title2,
                        color = Black,
                        modifier = modifier.padding(
                            top = 4.dp,
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
                            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            6.dp,
                            Alignment.CenterVertically
                        ),
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
                                top = 9.dp,
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
                }


                Column(
                    verticalArrangement = Arrangement.Top,
                ) {
                    InternPageTitle(modifier = modifier, text = "기업 정보")
                    InternCompanyInfo(modifier = modifier)
                    InternPageTitle(modifier = modifier, text = "공고 요약")
                    Column(
                        modifier = modifier
                            .padding(
                                start = 24.dp,
                                bottom = 16.dp,
                                end = 24.dp
                            )
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                3.dp,
                                Alignment.Start
                            ),
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                modifier = modifier
                                    .padding(end = 17.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_first_info_20),
                                    contentDescription = null
                                )
                                Text(
                                    text = "자격요건",
                                    style = TerningTheme.typography.button2,
                                    color = Black
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            ) {
                                Text(
                                    text = "졸업 예정자",
                                    style = TerningTheme.typography.body4,
                                    color = Grey400,
                                )
                                Text(
                                    text = "휴학생 가능",
                                    style = TerningTheme.typography.body4,
                                    color = Grey400,
                                )
                            }
                        }
                    }
                    Column(
                        modifier = modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                3.dp,
                                Alignment.Start
                            ),
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                modifier = modifier
                                    .padding(end = 40.dp)
                                    .wrapContentWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_second_info_20),
                                    contentDescription = null
                                )
                                Text(
                                    text = "직무",
                                    style = TerningTheme.typography.button2,
                                    color = Black
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            ) {
                                Text(
                                    text = "그래픽디자인",
                                    style = TerningTheme.typography.body4,
                                    color = Grey400,
                                )
                                Text(
                                    text = "UX/UI/GUI 디자인",
                                    style = TerningTheme.typography.body4,
                                    color = Grey400,
                                )
                            }
                        }
                    }
                    InternPageTitle(
                        modifier = modifier,
                        text = "상세 정보"
                    )
                    Column(
                        modifier = modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 20.dp
                        )
                    ) {
                        Text(
                            text = """
                        모니모니의 마케팅 팀은 소비자에게 삶의 솔루션으로서 ‘사랑’을 제시하는 아주 멋진 팀입니다.

                        ‘사랑’의 가치를 전 세계에 전파하기 위해 마케터는 그 누구보다 넓은 시야를 가져야 합니다. 거시적인 관점과 미시적인 관점을 모두 포괄할 수 있는 통찰력을 바탕으로 나아갑니다.

                        데이터에 근거하여 소통합니다. ‘사랑’,  ‘행복’. ‘같이’와 같은 추상적인 가치들을 수치로 가시화하는 아주 재미있는 작업을 함께합니다.
                    """.trimIndent(),
                            style = TerningTheme.typography.detail1,
                            color = Grey400
                        )
                    }
                }
            }
        }
    }
}