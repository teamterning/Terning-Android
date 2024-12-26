//package com.terning.feature.home.component
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.itemsIndexed
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberModalBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import com.terning.core.designsystem.R
//import com.terning.core.designsystem.component.bottomsheet.TerningBasicBottomSheet
//import com.terning.core.designsystem.component.button.ChangeFilterButton
//import com.terning.core.designsystem.component.button.RoundButton
//import com.terning.core.designsystem.extension.currentMonth
//import com.terning.core.designsystem.extension.currentYear
//import com.terning.core.designsystem.theme.Black
//import com.terning.core.designsystem.theme.Grey200
//import com.terning.core.designsystem.theme.TerningTheme
//import com.terning.core.designsystem.type.Grade
//import com.terning.core.designsystem.type.WorkingPeriod
//import kotlinx.collections.immutable.toImmutableList
//import java.util.Calendar
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeFilteringBottomSheet(
//    modifier: Modifier = Modifier,
//    defaultGrade: Grade?,
//    defaultWorkingPeriod: WorkingPeriod?,
//    defaultStartYear: Int?,
//    defaultStartMonth: Int?,
//    onDismiss: () -> Unit,
//    onChangeButtonClick: (String, String, Int, Int) -> Unit,
//) {
//    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//
//    var currentGrade by remember { mutableStateOf(defaultGrade) }
//    var currentPeriod by remember { mutableStateOf(defaultWorkingPeriod) }
//    var currentStartYear by remember {
//        mutableIntStateOf(
//            defaultStartYear ?: Calendar.getInstance().currentYear
//        )
//    }
//    var currentStartMonth by remember {
//        mutableIntStateOf(
//            defaultStartMonth ?: Calendar.getInstance().currentMonth
//        )
//    }
//
//    var isYearNull by remember { mutableStateOf(defaultStartYear != null) }
//    var isMonthNull by remember { mutableStateOf(defaultStartMonth != null) }
//
//    var isCheckBoxChecked by remember { mutableStateOf(false) }
//
//    var isInitialNullState by remember { mutableStateOf(false) }
//
//    val yearsList by remember(isYearNull) {
//        mutableStateOf(
//            if (isYearNull || isInitialNullState) years + NULL_DATE
//            else years
//        )
//    }
//    val monthsList by remember(isMonthNull) {
//        mutableStateOf(
//            if (isMonthNull || isInitialNullState) months + NULL_DATE
//            else months
//        )
//    }
//
//    TerningBasicBottomSheet(
//        content = {
//            Column(
//                modifier = modifier
//                    .fillMaxWidth(),
//            ) {
//                Text(
//                    text = stringResource(id = R.string.change_filter_top_bar_title),
//                    style = TerningTheme.typography.title2,
//                    color = Black,
//                    modifier = Modifier
//                        .padding(horizontal = 24.dp)
//                        .padding(bottom = 16.dp),
//                )
//
//                HorizontalDivider(
//                    thickness = 1.dp,
//                    color = Grey200,
//                    modifier = Modifier
//                        .padding(horizontal = 24.dp),
//                )
//
//                ChangeFilteringTitleText(
//                    text = stringResource(id = R.string.change_filter_grade_title),
//                    modifier = Modifier
//                        .padding(top = 18.dp, bottom = 12.dp)
//                        .padding(horizontal = 24.dp)
//                )
//
//                ChangeFilteringRadioGroup(
//                    initOption = defaultGrade?.ordinal ?: -1,
//                    optionList = listOf(
//                        R.string.change_filter_grade_1,
//                        R.string.change_filter_grade_2,
//                        R.string.change_filter_grade_3,
//                        R.string.change_filter_grade_4,
//                    ),
//                    onButtonClick = { index ->
//                        currentGrade = Grade.entries[index]
//                    },
//                    modifier = Modifier
//                        .padding(horizontal = 24.dp),
//                )
//
//                ChangeFilteringTitleText(
//                    text = stringResource(id = R.string.change_filter_period_title),
//                    modifier = Modifier
//                        .padding(top = 32.dp, bottom = 12.dp)
//                        .padding(horizontal = 24.dp)
//                )
//
//                ChangeFilteringRadioGroup(
//                    initOption = defaultWorkingPeriod?.ordinal ?: -1,
//                    optionList = listOf(
//                        R.string.change_filter_period_1,
//                        R.string.change_filter_period_2,
//                        R.string.change_filter_period_3,
//                    ),
//                    onButtonClick = { index ->
//                        currentPeriod = WorkingPeriod.entries[index]
//                    },
//                    modifier = Modifier
//                        .padding(horizontal = 24.dp),
//                )
//
//                ChangeFilteringTitleText(
//                    text = stringResource(id = R.string.change_filter_start_work_title),
//                    modifier = Modifier
//                        .padding(top = 32.dp, bottom = 49.dp)
//                        .padding(horizontal = 24.dp)
//                )
//
//                //TODO: 아래는 임시 체크박스로, 추후 수정 부탁합니다!
//                Checkbox(
//                    checked = isCheckBoxChecked,
//                    onCheckedChange = { isChecked ->
//                        if (isChecked) {
//                            isYearNull = true
//                            isMonthNull = true
//                        }
//                        isCheckBoxChecked = isChecked
//                    },
//                    modifier = Modifier.padding(start = 20.dp)
//                )
//
//                HomeYearMonthPicker(
//                    chosenYear = defaultStartYear,
//                    chosenMonth = defaultStartMonth,
//                    onYearChosen = { year, isInitialSelection ->
//                        if (year != null) {
//                            currentStartYear = year
//                            isCheckBoxChecked = false
//                            isYearNull = false
//                            isInitialNullState = isInitialSelection
//                        }
//                    },
//                    onMonthChosen = { month, isInitialSelection ->
//                        if (month != null) {
//                            currentStartMonth = month
//                            isCheckBoxChecked = false
//                            isMonthNull = false
//                            isInitialNullState = isInitialSelection
//                        }
//                    },
//                    isYearNull = isYearNull,
//                    isMonthNull = isMonthNull,
//                    yearsList = yearsList.toImmutableList(),
//                    monthsList = monthsList.toImmutableList(),
//                    isInitialNullState = isInitialNullState
//                )
//
//                RoundButton(
//                    style = TerningTheme.typography.button0,
//                    paddingVertical = 19.dp,
//                    text = R.string.change_filter_save,
//                    cornerRadius = 10.dp,
//                    modifier = Modifier
//                        .padding(horizontal = 24.dp)
//                        .padding(top = 51.dp),
//                    onButtonClick = {
//                        currentGrade?.let { grade ->
//                            currentPeriod?.let { workingPeriod ->
//                                onChangeButtonClick(
//                                    grade.stringValue,
//                                    workingPeriod.stringValue,
//                                    currentStartYear,
//                                    currentStartMonth,
//                                )
//                            }
//                        }
//                    },
//                    isEnabled = currentGrade != null && currentPeriod != null
//                )
//            }
//        },
//        onDismissRequest = onDismiss,
//        sheetState = sheetState,
//    )
//}
//
//@Composable
//fun ChangeFilteringTitleText(
//    text: String,
//    modifier: Modifier = Modifier,
//) {
//    Text(
//        text = text,
//        style = TerningTheme.typography.title4,
//        color = Black,
//        modifier = modifier,
//    )
//}
//
//@Composable
//fun ChangeFilteringRadioGroup(
//    optionList: List<Int>,
//    initOption: Int,
//    onButtonClick: (Int) -> Unit,
//    modifier: Modifier = Modifier,
//) {
//    var selectedIndex by remember { mutableIntStateOf(initOption) }
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(optionList.size),
//        horizontalArrangement = Arrangement.spacedBy(13.dp),
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//
//    ) {
//        itemsIndexed(optionList) { index, option ->
//            ChangeFilterButton(
//                isSelected = selectedIndex == index,
//                modifier = Modifier
//                    .wrapContentHeight(),
//                text = option,
//                cornerRadius = 10.dp,
//                paddingVertical = 10.dp,
//                onButtonClick = {
//                    selectedIndex = index
//                    onButtonClick(index)
//                }
//            )
//        }
//    }
//}