package com.terning.feature.home.changefilter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.datepicker.DatePickerUI
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.HomeFilteringInfoModel
import com.terning.domain.entity.request.ChangeFilteringRequestModel
import com.terning.feature.R
import com.terning.feature.home.changefilter.component.ChangeFilteringRadioGroup
import com.terning.feature.home.changefilter.component.FilteringMainTitleText
import com.terning.feature.home.changefilter.component.FilteringSubTitleText
import com.terning.feature.home.changefilter.navigation.navigateChangeFilter
import com.terning.feature.home.home.HomeSideEffect
import com.terning.feature.home.home.HomeViewModel

const val MIN_INDEX = 0
const val MAX_WORKING_INDEX = 2
const val MAX_GRADE_INDEX = 3

@Composable
fun ChangeFilterRoute(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val filteringState by viewModel.homeFilteringState.collectAsStateWithLifecycle()

    when (filteringState) {
        is UiState.Success -> ChangeFilterScreen(
            (filteringState as UiState.Success<HomeFilteringInfoModel>).data,
            navController,
            viewModel,
        )

        else -> {}
    }

    LaunchedEffect(viewModel.homeSideEffect, lifecycleOwner) {
        viewModel.homeSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is HomeSideEffect.NavigateToChangeFilter -> navController.navigateChangeFilter()
                    is HomeSideEffect.NavigateToHome -> navController.navigateUp()
                }
            }
    }
}

@Composable
fun ChangeFilterScreen(
    filterData: HomeFilteringInfoModel,
    navController: NavController,
    viewModel: HomeViewModel,
) {
    var currentGrade by remember { mutableIntStateOf(filterData.grade ?: -1) }
    var currentWorkingPeriod by remember { mutableIntStateOf(filterData.workingPeriod ?: -1) }
    var currentStartYear by remember {
        mutableIntStateOf(
            filterData.startYear ?: viewModel.currentYear
        )
    }
    var currentStartMonth by remember {
        mutableIntStateOf(
            filterData.startMonth ?: viewModel.currentMonth
        )
    }


    Scaffold(
        topBar = {
            BackButtonTopAppBar(
                title = stringResource(id = R.string.change_filter_top_bar_title),
                onBackButtonClick = { navController.popBackStack() },
                modifier = Modifier
                    .shadow(elevation = 2.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                )
        ) {
            ShowTitle(
                mainTitle = stringResource(id = R.string.change_filter_grade_main),
                subTitle = stringResource(id = R.string.filtering_status1_sub),
                modifier = Modifier.padding(
                    top = 31.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            )
            ChangeFilteringRadioGroup(
                initOption = filterData.grade ?: -1,
                optionList = listOf(
                    R.string.filtering_status1_button1,
                    R.string.filtering_status1_button2,
                    R.string.filtering_status1_button3,
                    R.string.filtering_status1_button4,
                ),
                onButtonClick = { index ->
                    currentGrade = index
                }
            )

            ShowTitle(
                mainTitle = stringResource(id = R.string.filtering_status2_title),
                subTitle = stringResource(id = R.string.filtering_status2_sub),
                modifier = Modifier.padding(
                    top = 39.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            )
            ChangeFilteringRadioGroup(
                initOption = filterData.workingPeriod ?: -1,
                optionList = listOf(
                    R.string.filtering_status2_button1,
                    R.string.filtering_status2_button2,
                    R.string.filtering_status2_button3,
                ),
                onButtonClick = { index ->
                    currentWorkingPeriod = index
                }
            )

            ShowTitle(
                mainTitle = stringResource(id = R.string.filtering_status3_title),
                subTitle = stringResource(id = R.string.filtering_status3_sub),
                modifier = Modifier.padding(
                    top = 39.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            )

            Spacer(modifier = Modifier.weight(1f))
            DatePickerUI(
                chosenYear = filterData.startYear ?: currentStartYear,
                chosenMonth = filterData.startMonth?.minus(1) ?: currentStartMonth,
                onYearChosen = { currentStartYear = it },
                onMonthChosen = { currentStartMonth = it }
            )
            Spacer(modifier = Modifier.weight(1f))

            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 19.dp,
                text = R.string.change_filter_save,
                onButtonClick = {
                    viewModel.putFilteringInfo(
                        ChangeFilteringRequestModel(
                            grade = currentGrade,
                            workingPeriod = currentWorkingPeriod,
                            startYear = currentStartYear,
                            startMonth = currentStartMonth,
                        )
                    )
                },
                isEnabled = currentGrade in MIN_INDEX..MAX_GRADE_INDEX && currentWorkingPeriod in MIN_INDEX..MAX_WORKING_INDEX
            )
        }
    }
}

@Composable
private fun ShowTitle(
    mainTitle: String,
    subTitle: String,
    modifier: Modifier = Modifier,
) {
    FilteringMainTitleText(
        text = mainTitle,
        modifier = modifier.padding()
    )
    FilteringSubTitleText(
        text = subTitle,
        modifier = Modifier
            .padding(
                top = 3.dp,
                bottom = 13.dp,
                start = 24.dp,
                end = 24.dp
            )
    )
}