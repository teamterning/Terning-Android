package com.terning.feature.home.changefilter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.datepicker.DatePickerUI
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.home.changefilter.component.ChangeFilteringRadioGroup
import com.terning.feature.home.changefilter.component.FilteringMainTitleText
import com.terning.feature.home.changefilter.component.FilteringSubTitleText
import com.terning.feature.home.home.HomeViewModel
import com.terning.feature.home.home.model.InternFilterData
import com.terning.feature.home.home.model.UserNameState
import com.terning.feature.home.home.navigation.navigateHome
import java.util.Calendar

val currentYear = Calendar.getInstance().get(Calendar.YEAR)
val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

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
    val isButtonValid = with(viewModel.userName.internFilter) {
        if (this?.grade == null) remember { mutableStateListOf(false, false) }
        else remember { mutableStateListOf(true, true) }
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
                internFilterData = viewModel.userName.internFilter,
                onButtonClick = {
                    viewModel.setGrade(it)
                    isButtonValid[0] = true
                }
            )

            UserNameState(
                userName = "남지우자랑스러운티엘이되",
                internFilter = InternFilterData(
                    grade = 4,
                    workingPeriod = 1,
                    startYear = 2024,
                    startMonth = 7,
                )
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
                internFilterData = viewModel.userName.internFilter,
                onButtonClick = {
                    viewModel.setWorkingPeriod(it)
                    isButtonValid[1] = true
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
                chosenYear = currentYear,
                chosenMonth = currentMonth,
            )
            Spacer(modifier = Modifier.weight(1f))

            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 19.dp,
                text = R.string.change_filter_save,
                onButtonClick = {
                    navController.navigateHome()
                },
                isEnabled = isButtonValid[0] && isButtonValid[1]
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