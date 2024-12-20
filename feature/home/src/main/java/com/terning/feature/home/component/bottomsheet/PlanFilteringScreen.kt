package com.terning.feature.home.component.bottomsheet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.R
import com.terning.core.designsystem.extension.currentMonth
import com.terning.core.designsystem.extension.currentYear
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.type.Grade
import com.terning.core.designsystem.type.WorkingPeriod
import com.terning.domain.home.entity.HomeFilteringInfo
import java.util.Calendar

@Composable
fun PlanFilteringScreen(
    currentFilteringInfo: HomeFilteringInfo,
    updateGrade: (Int) -> Unit,
    updateWorkingPeriod: (Int) -> Unit,
    updateStartYear: (Int) -> Unit,
    updateStartMonth: (Int) -> Unit,
) {
    Text(
        text = stringResource(id = R.string.change_filter_top_bar_title),
        style = TerningTheme.typography.title2,
        color = Black,
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 16.dp),
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = Grey200,
        modifier = Modifier
            .padding(horizontal = 24.dp),
    )

    ChangeFilteringTitleText(
        text = stringResource(id = R.string.change_filter_grade_title),
        modifier = Modifier
            .padding(top = 18.dp, bottom = 12.dp)
            .padding(horizontal = 24.dp)
    )

    ChangeFilteringRadioGroup(
        initOption = currentFilteringInfo.grade?.let { Grade.fromString(it).ordinal } ?: -1,
        optionList = listOf(
            R.string.change_filter_grade_1,
            R.string.change_filter_grade_2,
            R.string.change_filter_grade_3,
            R.string.change_filter_grade_4,
        ),
        onButtonClick = updateGrade,
        columns = 4,
        modifier = Modifier
            .padding(horizontal = 24.dp),
    )

    ChangeFilteringTitleText(
        text = stringResource(id = R.string.change_filter_period_title),
        modifier = Modifier
            .padding(top = 32.dp, bottom = 12.dp)
            .padding(horizontal = 24.dp)
    )

    ChangeFilteringRadioGroup(
        initOption = currentFilteringInfo.workingPeriod?.let { WorkingPeriod.fromString(it).ordinal }
            ?: -1,
        optionList = listOf(
            R.string.change_filter_period_1,
            R.string.change_filter_period_2,
            R.string.change_filter_period_3,
        ),
        onButtonClick = updateWorkingPeriod,
        modifier = Modifier
            .padding(horizontal = 24.dp),
    )

    ChangeFilteringTitleText(
        text = stringResource(id = R.string.change_filter_start_work_title),
        modifier = Modifier
            .padding(top = 32.dp, bottom = 49.dp)
            .padding(horizontal = 24.dp)
    )

    YearMonthPicker(
        chosenYear = currentFilteringInfo.startYear ?: Calendar.getInstance().currentYear,
        chosenMonth = currentFilteringInfo.startMonth
            ?: Calendar.getInstance().currentMonth,
        onYearChosen = updateStartYear,
        onMonthChosen = updateStartMonth,
    )
}