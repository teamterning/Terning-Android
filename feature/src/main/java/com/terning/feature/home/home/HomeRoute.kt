package com.terning.feature.home.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.response.HomeFilteringInfoModel
import com.terning.domain.entity.response.HomeRecommendInternModel
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.feature.R
import com.terning.feature.home.changefilter.navigation.navigateChangeFilter
import com.terning.feature.home.home.component.HomeFilteringEmptyIntern
import com.terning.feature.home.home.component.HomeFilteringScreen
import com.terning.feature.home.home.component.HomeRecommendEmptyIntern
import com.terning.feature.home.home.component.HomeTodayEmptyIntern
import com.terning.feature.home.home.component.HomeTodayIntern
import com.terning.feature.home.home.navigation.navigateHome
import com.terning.feature.intern.navigation.navigateIntern

const val NAME_START_LENGTH = 7
const val NAME_END_LENGTH = 12

@Composable
fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
        systemUiController.setNavigationBarColor(
            color = White
        )
    }

    val currentSortBy: MutableState<Int> = remember {
        mutableIntStateOf(0)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val homeTodayState by viewModel.homeTodayState.collectAsStateWithLifecycle()
    val homeRecommendInternState by viewModel.homeRecommendInternState.collectAsStateWithLifecycle()
    val homeFilteringState by viewModel.homeFilteringState.collectAsStateWithLifecycle()
    val homeUserState by viewModel.homeUserState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.homeSideEffect, lifecycleOwner) {
        viewModel.homeSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is HomeSideEffect.NavigateToChangeFilter -> navController.navigateChangeFilter()
                    is HomeSideEffect.NavigateToHome -> navController.navigateHome()
                }
            }
    }

    LaunchedEffect(currentSortBy.value) {
        when (homeFilteringState) {
            is UiState.Success ->
                with((homeFilteringState as UiState.Success<HomeFilteringInfoModel>).data) {
                    viewModel.getRecommendInternsData(
                        currentSortBy.value,
                        startYear ?: viewModel.currentYear,
                        startMonth ?: viewModel.currentMonth
                    )
                }

            else -> {}
        }
    }

    val homeTodayInternList = when (homeTodayState) {
        is UiState.Success -> {
            (homeTodayState as UiState.Success<List<HomeTodayInternModel>>).data
        }

        else -> emptyList()
    }

    val homeRecommendInternList = when (homeRecommendInternState) {
        is UiState.Success -> {
            (homeRecommendInternState as UiState.Success<List<HomeRecommendInternModel>>).data
        }

        else -> emptyList()
    }

    val homeFilteringInfo = when (homeFilteringState) {
        is UiState.Success -> (homeFilteringState as UiState.Success<HomeFilteringInfoModel>).data
        else -> HomeFilteringInfoModel(null, null, viewModel.currentYear, viewModel.currentMonth)
    }

    val homeUserName = when (homeUserState) {
        is UiState.Success -> (homeUserState as UiState.Success<String>).data
        else -> ""
    }

    HomeScreen(
        currentSortBy,
        homeUserName = homeUserName,
        homeFilteringInfo = homeFilteringInfo,
        homeTodayInternList = homeTodayInternList,
        recommendInternList = homeRecommendInternList,
        onChangeFilterClick = { navController.navigateChangeFilter() },
        navController = navController
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    currentSortBy: MutableState<Int>,
    homeUserName: String,
    homeFilteringInfo: HomeFilteringInfoModel,
    homeTodayInternList: List<HomeTodayInternModel>,
    recommendInternList: List<HomeRecommendInternModel>,
    onChangeFilterClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
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
                        ShowMainTitleWithName(homeUserName)
                        ShowTodayIntern(homeTodayInternList = homeTodayInternList)
                    }
                }
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .background(White)
                    ) {
                        ShowRecommendTitle()
                        ShowInternFilter(homeFilteringInfo = homeFilteringInfo, onChangeFilterClick)

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
                            Spacer(
                                modifier = Modifier.padding(9.dp)
                            )
                        }
                    }
                }

                if (recommendInternList.isNotEmpty()) {
                    items(recommendInternList.size) { index ->
                        RecommendInternItem(
                            navController = navController,
                            intern = recommendInternList[index]
                        )
                    }
                }
            }

            if (homeFilteringInfo.grade == null) {
                HomeFilteringEmptyIntern(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxSize()
                )
            } else if (recommendInternList.isEmpty()) {
                HomeRecommendEmptyIntern()
            }
        }
    }
}


@Composable
private fun RecommendInternItem(
    navController: NavHostController,
    intern: HomeRecommendInternModel,
) {
    InternItemWithShadow(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .noRippleClickable {
                navController.navigateIntern(
                    announcementId = intern.internshipAnnouncementId
                )
            },
        imageUrl = intern.companyImage,
        title = intern.title,
        dateDeadline = intern.dDay,
        workingPeriod = intern.workingPeriod,
        isScrapped = intern.isScrapped,
        shadowRadius = 5.dp,
        shadowWidth = 1.dp
    )
}

@Composable
private fun ShowMainTitleWithName(userName: String) {
    Text(
        text = stringResource(
            id = R.string.home_today_title,
            if (userName.length in NAME_START_LENGTH..NAME_END_LENGTH) "\n" + userName
            else userName
        ),
        modifier = Modifier
            .padding(top = 11.dp, bottom = 19.dp)
            .padding(horizontal = 24.dp),
        style = TerningTheme.typography.title1,
        color = Black,
    )
}

@Composable
private fun ShowTodayIntern(homeTodayInternList: List<HomeTodayInternModel>) {
    if (homeTodayInternList.isEmpty()) {
        HomeTodayEmptyIntern(isButtonExist = false)
    } else {
        HomeTodayIntern(internList = homeTodayInternList)
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
private fun ShowInternFilter(
    homeFilteringInfo: HomeFilteringInfoModel,
    onChangeFilterClick: () -> Unit,
) {
    if (homeFilteringInfo.grade == null) {
        HomeFilteringScreen(
            grade = stringResource(id = R.string.home_recommend_no_filtering_hyphen),
            period = stringResource(id = R.string.home_recommend_no_filtering_hyphen),
            startYear = stringResource(id = R.string.home_recommend_no_filtering_hyphen),
            onChangeFilterClick = { onChangeFilterClick() },
        )
    } else {
        with(homeFilteringInfo) {
            HomeFilteringScreen(
                grade = (grade?.plus(1)).toString() + stringResource(id = R.string.home_recommend_filtering_grade),
                period = stringResource(
                    id = when (workingPeriod) {
                        0 -> R.string.filtering_status2_button1
                        1 -> R.string.filtering_status2_button2
                        2 -> R.string.filtering_status2_button3
                        else -> R.string.home_recommend_no_filtering_hyphen
                    }
                ),
                startYear = startYear.toString() + stringResource(id = R.string.home_recommend_filtering_startYear)
                        + "  " + startMonth.toString() + stringResource(id = R.string.home_recommend_filtering_startMonth),
                onChangeFilterClick = { onChangeFilterClick() },
            )
        }
    }
}
