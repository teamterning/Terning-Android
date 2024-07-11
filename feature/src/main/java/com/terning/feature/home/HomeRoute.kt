package com.terning.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.R
import com.terning.feature.home.component.HomeFilteringScreen
import com.terning.feature.home.component.HomeTodayIntern
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar

@Composable
fun HomeRoute() {
    HomeScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier,
        topBar = {
            LogoTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.home_today_title_start
                            )
                                    + "남지우"
                                    + stringResource(
                                id = R.string.home_today_title_end
                            ),
                            modifier = Modifier
                                .padding(top = 11.dp)
                                .padding(horizontal = 24.dp),
                            style = TerningTheme.typography.title1,
                            color = Black,
                        )
                        HomeTodayIntern()
                    }
                }
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .background(White)
                    ) {
                        Text(
                            text = stringResource(id = R.string.home_recommend_sub_title),
                            style = TerningTheme.typography.detail2,
                            color = Black,
                            modifier = Modifier
                                .padding(top = 25.dp)
                                .padding(horizontal = 24.dp),
                        )

                        Text(
                            text = stringResource(id = R.string.home_recommend_main_title),
                            style = TerningTheme.typography.title1,
                            color = Black,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .padding(horizontal = 24.dp),
                        )

                        HomeFilteringScreen(3, 1, 7)

                        HorizontalDivider(
                            thickness = 4.dp,
                            color = Grey150,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }
                }

                items(10) {
                    TerningPostItem(
                        imageUrl = "https://reqres.in/img/faces/7-image.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용",
                        dateDeadline = "2",
                        workingPeriod = "2개월",
                        isScraped = false,
                    )
                }
            }
        }
    }
}
