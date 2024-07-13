package com.terning.feature.home.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.item.InternItem
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.feature.R
import com.terning.feature.home.home.component.HomeFilteringEmptyIntern
import com.terning.feature.home.changefilter.navigation.navigateChangeFilter
import com.terning.feature.home.home.component.HomeFilteringScreen
import com.terning.feature.home.home.component.HomeRecommendEmptyIntern
import com.terning.feature.home.home.component.HomeTodayEmptyIntern
import com.terning.feature.home.home.component.HomeTodayIntern
import com.terning.feature.home.home.model.RecommendInternData
import com.terning.feature.home.home.model.UserNameState
import com.terning.feature.home.home.model.UserScrapState

const val NAME_START_LENGTH = 7
const val NAME_END_LENGTH = 12

@Composable
fun HomeRoute(
    navController: NavHostController
) {
    val currentSortBy: MutableState<Int> = remember {
        mutableIntStateOf(0)
    }
    HomeScreen(currentSortBy,navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    currentSortBy: MutableState<Int>,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val userNameState = viewModel.userName
    val userScrapState by viewModel.scrapData.collectAsStateWithLifecycle()
    val recommendInternData by viewModel.recommendInternData.collectAsStateWithLifecycle()
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
                contentPadding = PaddingValues(
                    top = 2.dp,
                    bottom = 20.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    ) {
                        ShowMainTitleWithName(userNameState)
                        ShowTodayIntern(userScrapState = userScrapState)
                    }
                }
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .background(White)
                    ) {
                        ShowRecommendTitle()
                        ShowInternFilter(userNameState = userNameState)

                        HorizontalDivider(
                            thickness = 4.dp,
                            color = Grey150,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            SortingButton(
                                sortBy = currentSortBy.value,
                                onCLick = { sheetState = true },
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                            )
                            Spacer(modifier = Modifier.padding(9.dp))
                        }
                    }
                }

                if (userNameState.internFilter != null && recommendInternData.isNotEmpty()) {
                    items(recommendInternData.size) { index ->
                        ShowRecommendIntern(recommendInternData[index])
                    }
                }
            }

            if (userNameState.internFilter == null) {
                HomeFilteringEmptyIntern(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxSize()
                )
            } else if (recommendInternData.isEmpty()) {
                HomeRecommendEmptyIntern()
            }
        }
    }
}

@Composable
private fun ShowMainTitleWithName(userNameState: UserNameState) {
    Text(
        text = stringResource(
            id = R.string.home_today_title,
            if (userNameState.userName.length in NAME_START_LENGTH..NAME_END_LENGTH) "\n" + userNameState.userName
            else userNameState.userName
        ),
        modifier = Modifier
            .padding(top = 11.dp, bottom = 19.dp)
            .padding(horizontal = 24.dp),
        style = TerningTheme.typography.title1,
        color = Black,
    )
}

@Composable
private fun ShowTodayIntern(userScrapState: UserScrapState) {
    if (userScrapState.isScrapExist) {
        if (userScrapState.scrapData == null) {
            HomeTodayEmptyIntern(isButtonExist = true)
        } else {
            HomeTodayIntern(userScrapState.scrapData)
        }
    } else {
        HomeTodayEmptyIntern(isButtonExist = false)
    }
}

@Composable
private fun ShowRecommendTitle() {
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
}

@Composable
private fun ShowInternFilter(userNameState: UserNameState) {
    if (userNameState.internFilter == null) {
        HomeFilteringScreen(
            grade = R.string.home_recommend_no_filtering_hyphen,
            period = R.string.home_recommend_no_filtering_hyphen,
            startYear = R.string.home_recommend_no_filtering_hyphen,
            startMonth = R.string.home_recommend_no_filtering_hyphen,
            onChangeFilterClick = { navController.navigateChangeFilter() },
        )
    } else {
        with(userNameState.internFilter) {
            HomeFilteringScreen(
                grade = grade,
                period = workingPeriod,
                startYear = startYear,
                startMonth = startMonth,
                onChangeFilterClick = { navController.navigateChangeFilter() },
            )
        }
    }
}

@Composable
private fun ShowRecommendIntern(recommendInternData: RecommendInternData) {
    Box(
        modifier = Modifier
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
            imageUrl = recommendInternData.imgUrl,
            title = recommendInternData.title,
            dateDeadline = recommendInternData.dDay.toString(),
            workingPeriod = recommendInternData.workingPeriod.toString(),
            isScraped = recommendInternData.isScrapped,
        )
    }
}