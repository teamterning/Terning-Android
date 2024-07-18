package com.terning.feature.home.changefilter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.terning.domain.entity.request.ChangeFilteringRequestModel
import com.terning.domain.entity.response.HomeFilteringInfoModel
import com.terning.feature.R
import com.terning.feature.home.changefilter.component.ChangeFilteringRadioGroup
import com.terning.feature.home.changefilter.component.FilteringMainTitleText
import com.terning.feature.home.changefilter.component.FilteringSubTitleText
import com.terning.feature.home.changefilter.navigation.navigateChangeFilter
import com.terning.feature.home.home.HomeSideEffect
import com.terning.feature.home.home.HomeViewModel
import com.terning.feature.home.home.navigation.navigateHome

@Composable
fun ChangeFilterRoute(
    navController: NavController,
) {
    ChangeFilterScreen(navController)
}

@Composable
fun ChangeFilterScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val filteringState by viewModel.homeFilteringState.collectAsStateWithLifecycle()
    val filterData = when (filteringState) {
        is UiState.Success -> (filteringState as UiState.Success<HomeFilteringInfoModel>).data
        else -> HomeFilteringInfoModel(null, null, viewModel.currentYear, viewModel.currentMonth)
    }

    var isGradeButtonValid = (filterData.grade != null)
    var isWorkingPeriodButtonValid = (filterData.workingPeriod != null)

    var currentGrade = -1
    var currentWorkingPeriod = -1
    var currentStartYear = -1
    var currentStartMonth = -1

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
                filterType = 0,
                filterData = filterData,
                onButtonClick = { index ->
                    isGradeButtonValid = true
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
                filterType = 1,
                filterData = filterData,
                onButtonClick = { index ->
                    isWorkingPeriodButtonValid = true
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
                chosenMonth = filterData.startMonth ?: currentStartMonth,
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
                isEnabled = isGradeButtonValid && isWorkingPeriodButtonValid
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