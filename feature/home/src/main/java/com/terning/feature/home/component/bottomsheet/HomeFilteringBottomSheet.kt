package com.terning.feature.home.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.bottomsheet.TerningBasicBottomSheet
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.type.Grade
import com.terning.core.designsystem.type.WorkingPeriod
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.feature.home.R
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

val filterType =
    listOf(R.string.change_job_type_filter, R.string.change_plan_type_filter).toImmutableList()

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
    val pagerState = rememberPagerState { 2 }
    val coroutineScope = rememberCoroutineScope()

    TerningBasicBottomSheet(
        content = {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(id = R.string.change_filter_top_bar_title),
                    style = TerningTheme.typography.title2,
                    color = Black,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 16.dp),
                )

                Row(
                    modifier = Modifier
                        .padding(horizontal = 23.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    filterType.forEachIndexed { index, option ->
                        if (index == 1) {
                            Spacer(modifier = Modifier.width(28.dp))
                        }
                        TerningTab(
                            tabText = stringResource(id = option),
                            selected = pagerState.currentPage == index,
                            onTabClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            })
                    }
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Grey200,
                    modifier = Modifier
                        .padding(horizontal = 24.dp),
                )

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    if (pagerState.currentPage == 0) {
                        JobFilteringScreen(initOption = 0, onButtonClick = {})
                    } else {
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
                    }
                }

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
fun TerningTab(
    tabText: String,
    selected: Boolean,
    onTabClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .noRippleClickable { onTabClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = tabText,
            style = TerningTheme.typography.title4,
            color = if (selected) TerningMain else Grey300,
            modifier = Modifier
                .padding(bottom = 7.dp),
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .background(if (selected) TerningMain else Color.Transparent)
        )
    }
}