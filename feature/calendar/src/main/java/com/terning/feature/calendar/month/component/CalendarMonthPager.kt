package com.terning.feature.calendar.month.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.domain.calendar.entity.CalendarScrap
import com.terning.feature.calendar.calendar.component.group.CalendarMonthGroup
import com.terning.feature.calendar.calendar.model.DayModel
import com.terning.feature.calendar.calendar.model.TerningCalendarModel
import java.time.LocalDate


@Composable
internal fun CalendarMonthPager(
    pagerState: PagerState,
    calendarModel: TerningCalendarModel,
    onDateSelect: (DayModel) -> Unit,
    selectedDate: DayModel,
    modifier: Modifier = Modifier,
    scrapMap: Map<String, List<CalendarScrap>>,
    isWeekEnabled: Boolean = false,
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize()
    ) { page ->
        val monthModel = calendarModel.getMonthModelByPage(page = page)

        CalendarMonthGroup(
            isWeekEnabled = isWeekEnabled,
            dayModels = monthModel.calendarMonth,
            modifier = Modifier.fillMaxSize(),
            onDateSelected = onDateSelect,
            selectedDate = selectedDate,
            scrapMap = scrapMap
        )
    }
}

@Preview
@Composable
private fun CalendarMonthScreenPreview() {
    TerningPointTheme {
        val calendarModel = TerningCalendarModel()
        val selectedDay = DayModel(LocalDate.now())
        val pagerState = rememberPagerState(
            initialPage = calendarModel.initialPage,
            pageCount = { calendarModel.pageCount }
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val monthModel = calendarModel.getMonthModelByPage(page = page)

            CalendarMonthGroup(
                isWeekEnabled = false,
                dayModels = monthModel.calendarMonth,
                modifier = Modifier.fillMaxSize(),
                onDateSelected = { },
                selectedDate = selectedDay,
                scrapMap = mapOf(
                    "2024-12-11" to listOf(
                        CalendarScrap(
                            scrapId = 1,
                            title = "테스트1",
                            deadLine = "2024-12-11",
                            isScrapped = true,
                            color = "#a3d711"
                        )
                    )
                )
            )
        }
    }
}