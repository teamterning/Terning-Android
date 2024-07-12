package com.terning.feature.home.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.item.InternItem
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.feature.R
import com.terning.feature.home.home.component.HomeFilteringEmptyIntern
import com.terning.feature.home.home.component.HomeFilteringScreen
import com.terning.feature.home.home.component.HomeTodayIntern

@Composable
fun HomeRoute() {
    val currentSortBy: MutableState<Int> = remember {
        mutableStateOf(0)
    }

    HomeScreen(currentSortBy)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    currentSortBy: MutableState<Int>,
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val userNameState = viewModel.userName
    var sheetState by remember { mutableStateOf(false) }

    if (sheetState) {
        SortingBottomSheet(
            onDismiss = {
                sheetState = false
            },
            currentSortBy = currentSortBy.value,
            newSortBy = currentSortBy
        )
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            LogoTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(2.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.home_today_title,
                                if (userNameState.userName.length in 7..12) "\n" + userNameState.userName
                                else userNameState.userName
                            ),
                            modifier = Modifier
                                .padding(top = 11.dp, bottom = 19.dp)
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
                                .padding(top = 9.dp)
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

                        if(userNameState.internFilter == null) {
                            HomeFilteringScreen(
                                grade = R.string.home_recommend_no_filtering_hyphen,
                                period = R.string.home_recommend_no_filtering_hyphen,
                                startYear = R.string.home_recommend_no_filtering_hyphen,
                                startMonth = R.string.home_recommend_no_filtering_hyphen
                            )
                        } else {
                            with(userNameState.internFilter) {
                                HomeFilteringScreen(
                                    grade = get(0),
                                    period = get(1),
                                    startYear = get(2),
                                    startMonth = get(3),
                                )
                            }
                        }

                        HorizontalDivider(
                            thickness = 4.dp,
                            color = Grey150,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }
                }

                if(userNameState.internFilter != null) {
                    items(itemCount) {
                        Box(
                            modifier = modifier
                                .height(92.dp)
                                .padding(horizontal = 24.dp)
                                .customShadow(
                                    color = Grey200,
                                    shadowRadius = 10.dp,
                                    shadowWidth = 2.dp
                                )
                                .background(
                                    color = White,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            InternItem(
                                imageUrl = "https://reqres.in/img/faces/7-image.jpg",
                                title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용",
                                dateDeadline = "2",
                                workingPeriod = "2",
                                isScraped = false,
                            )
                        }
                    }
                }

            }
            if(userNameState.internFilter == null) {
                HomeFilteringEmptyIntern(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

private const val itemCount = 10