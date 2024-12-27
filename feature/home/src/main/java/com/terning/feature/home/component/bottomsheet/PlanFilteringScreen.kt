package com.terning.feature.home.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.button.ChangeFilterButton
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.designsystem.type.Grade
import com.terning.core.designsystem.type.WorkingPeriod
import com.terning.core.designsystem.util.NoRippleInteractionSource
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.feature.home.R
import com.terning.feature.home.component.HomeYearMonthPicker
import com.terning.feature.home.component.NULL_DATE
import com.terning.feature.home.component.months
import com.terning.feature.home.component.years
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanFilteringScreen(
    currentFilteringInfo: HomeFilteringInfo,
    updateGrade: (Int) -> Unit,
    updateWorkingPeriod: (Int) -> Unit,
    updateStartYear: (Int) -> Unit,
    updateStartMonth: (Int) -> Unit,
) {
    var isYearNull by remember { mutableStateOf(currentFilteringInfo.startYear == null) }
    var isMonthNull by remember { mutableStateOf(currentFilteringInfo.startMonth == null) }

    var isCheckBoxChecked by remember { mutableStateOf(false) }

    var isInitialNullState by remember { mutableStateOf(false) }

    val yearsList by remember(isYearNull) {
        mutableStateOf(
            if (isYearNull || isInitialNullState) years + NULL_DATE
            else years
        )
    }
    val monthsList by remember(isMonthNull) {
        mutableStateOf(
            if (isMonthNull || isInitialNullState) months + NULL_DATE
            else months
        )
    }

    Column {
        ChangeFilteringTitleText(
            text = stringResource(id = R.string.change_filter_grade_title),
            modifier = Modifier
                .padding(start = 23.dp, end = 23.dp, bottom = 12.dp)
        )

        ChangePlanFilteringRadioGroup(
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

        ChangePlanFilteringRadioGroup(
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

        HomeYearMonthPicker(
            chosenYear = currentFilteringInfo.startYear,
            chosenMonth = currentFilteringInfo.startMonth,
            onYearChosen = { year, isInitialSelection ->
                if (year != null) {
                    updateStartYear(year)
                    isCheckBoxChecked = false
                    isYearNull = false
                    isInitialNullState = isInitialSelection
                }
            },
            onMonthChosen = { month, isInitialSelection ->
                if (month != null) {
                    updateStartMonth(month)
                    isCheckBoxChecked = false
                    isMonthNull = false
                    isInitialNullState = isInitialSelection
                }
            },
            isYearNull = isYearNull,
            isMonthNull = isMonthNull,
            yearsList = yearsList.toImmutableList(),
            monthsList = monthsList.toImmutableList(),
            isInitialNullState = isInitialNullState
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp, top = 26.dp, end = 24.dp),
        ) {
            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                Checkbox(
                    checked = isCheckBoxChecked,
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            isYearNull = true
                            isMonthNull = true
                        }
                        isCheckBoxChecked = isChecked
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = TerningMain,
                        uncheckedColor = Grey300,
                        checkmarkColor = White,
                    ),
                    interactionSource = NoRippleInteractionSource
                )
            }

            Text(
                text = stringResource(id = R.string.intern_with_no_plan_filter),
                style = TerningTheme.typography.button3,
                color = Grey300,
                modifier = Modifier.padding(start = 6.dp),
            )
        }
    }
}

@Composable
fun ChangePlanFilteringRadioGroup(
    optionList: List<Int>,
    initOption: Int,
    onButtonClick: (Int) -> Unit,
    columns: Int = 3,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember { mutableIntStateOf(initOption) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        horizontalArrangement = Arrangement.spacedBy(13.dp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        itemsIndexed(optionList) { index, option ->
            ChangeFilterButton(
                isSelected = selectedIndex == index,
                modifier = Modifier
                    .wrapContentHeight(),
                text = option,
                cornerRadius = 10.dp,
                paddingVertical = 10.dp,
                onButtonClick = {
                    selectedIndex = index
                    onButtonClick(index)
                }
            )
        }
    }
}