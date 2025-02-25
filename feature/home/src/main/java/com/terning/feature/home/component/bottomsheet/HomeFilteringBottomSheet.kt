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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.bottomsheet.TerningBasicBottomSheet
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.type.Grade
import com.terning.core.designsystem.type.JobType
import com.terning.core.designsystem.type.WorkingPeriod
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.feature.home.R
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

private val filterType =
    listOf(R.string.change_job_type_filter, R.string.change_plan_type_filter).toImmutableList()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeFilteringBottomSheet(
    defaultFilteringInfo: HomeFilteringInfo,
    onDismiss: () -> Unit,
    onChangeButtonClick: (String?, String?, Int?, Int?, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var currentFilteringInfo by remember {
        mutableStateOf(defaultFilteringInfo)
    }
    val pagerState = rememberPagerState { filterType.size }
    val coroutineScope = rememberCoroutineScope()

    val density = LocalDensity.current
    var pageHeight by remember { mutableIntStateOf(0) }

    var isCheckBoxChecked by remember {
        mutableStateOf(
            with(currentFilteringInfo) {
                listOf(grade, workingPeriod, startYear, startMonth).all { it == null || it == 0 }
            }
        )
    }

    val amplitudeTracker = LocalTracker.current

    GetPagerHeight(
        onHeightMeasured = {
            pageHeight = it
        }
    )

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
                    horizontalArrangement = Arrangement.spacedBy(28.dp),
                ) {
                    filterType.forEachIndexed { index, option ->
                        TerningTab(
                            tabText = stringResource(id = option),
                            selected = pagerState.currentPage == index,
                            onTabClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                        )
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
                    beyondViewportPageCount = 1,
                    modifier = Modifier
                        .padding(top = 16.dp),
                ) { currentPage ->
                    when (currentPage) {
                        0 -> {
                            JobFilteringScreen(
                                initOption = JobType.fromString(currentFilteringInfo.jobType).ordinal,
                                onButtonClick = { jobType ->
                                    currentFilteringInfo = currentFilteringInfo.copy(
                                        jobType = jobType.stringValue
                                    )
                                },
                                modifier = Modifier.height(with(density) { pageHeight.toDp() })
                            )
                        }

                        1 -> {
                            PlanFilteringScreen(
                                currentFilteringInfo = currentFilteringInfo,
                                isCheckBoxChecked = isCheckBoxChecked,
                                updateGrade = {
                                    currentFilteringInfo = currentFilteringInfo.copy(
                                        grade = if (it != null) {
                                            Grade.entries[it].stringValue
                                        } else null
                                    )
                                },
                                updateWorkingPeriod = {
                                    currentFilteringInfo = currentFilteringInfo.copy(
                                        workingPeriod = if (it != null) {
                                            WorkingPeriod.entries[it].stringValue
                                        } else null
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
                                updateIsCheckBoxChecked = {
                                    isCheckBoxChecked = it
                                }
                            )
                        }
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
                        with(currentFilteringInfo) {
                            onChangeButtonClick(
                                grade,
                                workingPeriod,
                                startYear,
                                startMonth,
                                jobType
                            )
                            amplitudeTracker.track(
                                type = EventType.CLICK,
                                name = "home_filtering_save",
                                properties = mapOf(
                                    "jobType" to jobType,
                                    "grade" to grade,
                                    "workingPeriod" to workingPeriod,
                                    "startYear" to startYear,
                                    "startMonth" to startMonth,
                                    "planSaveAll" to isCheckBoxChecked
                                )
                            )
                        }
                    },
                    isEnabled = checkButtonEnable(
                        currentFilteringInfo = currentFilteringInfo,
                        isCheckBoxChecked = isCheckBoxChecked
                    )
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

private fun checkButtonEnable(
    currentFilteringInfo: HomeFilteringInfo,
    isCheckBoxChecked: Boolean
): Boolean =
    with(currentFilteringInfo) {
        isCheckBoxChecked || listOf(grade, workingPeriod, startYear, startMonth).none { it == null }
    }

@Composable
private fun GetPagerHeight(
    onHeightMeasured: (Int) -> Unit,
) {
    PlanFilteringScreen(
        currentFilteringInfo = HomeFilteringInfo(null, null, null, null, "total"),
        isCheckBoxChecked = false,
        modifier = Modifier
            .onGloballyPositioned {
                onHeightMeasured(it.size.height)
            }
    )
}