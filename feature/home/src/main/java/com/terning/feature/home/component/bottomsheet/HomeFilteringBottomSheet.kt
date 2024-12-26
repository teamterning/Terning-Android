package com.terning.feature.home.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.R
import com.terning.core.designsystem.component.bottomsheet.TerningBasicBottomSheet
import com.terning.core.designsystem.component.button.ChangeFilterButton
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.type.Grade
import com.terning.core.designsystem.type.WorkingPeriod
import com.terning.domain.home.entity.HomeFilteringInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFilteringBottomSheet(
    modifier: Modifier = Modifier,
    defaultFilteringInfo: HomeFilteringInfo,
    onDismiss: () -> Unit,
    onChangeButtonClick: (String, String, Int, Int) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var currentFilteringInfo by remember {
        mutableStateOf(defaultFilteringInfo)
    }

    TerningBasicBottomSheet(
        content = {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                PlanFilteringScreen(
                    currentFilteringInfo = currentFilteringInfo,
                    updateGrade = {
                        currentFilteringInfo = currentFilteringInfo.copy(
                            grade = Grade.entries[it].stringValue
                        )
                    },
                    updateWorkingPeriod = {
                        currentFilteringInfo = currentFilteringInfo.copy(
                            workingPeriod = WorkingPeriod.entries[it].stringValue
                        )
                    },
                    updateStartYear = {
                        currentFilteringInfo = currentFilteringInfo.copy(
                            startYear = it
                        )
                    },
                    updateStartMonth = {
                        currentFilteringInfo = currentFilteringInfo.copy(
                            startMonth = it
                        )
                    },
                )

                RoundButton(
                    style = TerningTheme.typography.button0,
                    paddingVertical = 19.dp,
                    text = R.string.change_filter_save,
                    cornerRadius = 10.dp,
                    modifier = Modifier
                        .padding(horizontal = 24.dp),
                    onButtonClick = {
                        currentFilteringInfo.grade?.let {
                            currentFilteringInfo.workingPeriod?.let { it1 ->
                                currentFilteringInfo.startYear?.let { it2 ->
                                    currentFilteringInfo.startMonth?.let { it3 ->
                                        onChangeButtonClick(
                                            it,
                                            it1,
                                            it2,
                                            it3
                                        )
                                    }
                                }
                            }
                        }
                    },
                    isEnabled = currentFilteringInfo.grade != null && currentFilteringInfo.workingPeriod != null
                )
            }

        },
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    )
}

@Composable
fun ChangeFilteringTitleText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = TerningTheme.typography.title4,
        color = Black,
        modifier = modifier,
    )
}

@Composable
fun ChangeFilteringRadioGroup(
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