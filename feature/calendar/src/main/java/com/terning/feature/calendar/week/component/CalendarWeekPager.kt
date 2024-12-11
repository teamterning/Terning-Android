package com.terning.feature.calendar.week.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.calendar.calendar.component.group.CalendarWeekGroup
import com.terning.feature.calendar.calendar.model.DayModel
import com.terning.feature.calendar.calendar.model.MonthModel
import com.terning.feature.calendar.calendar.model.TerningCalendarModel
import java.time.LocalDate

@Composable
internal fun CalendarWeekPager(
    monthModel: MonthModel,
    onDateSelect: (DayModel) -> Unit,
    selectedDate: DayModel,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        initialPage = selectedDate.weekIndex,
        pageCount = { monthModel.calendarMonth.size }
    )

    LaunchedEffect(selectedDate) {
        val page = selectedDate.weekIndex
        pagerState.animateScrollToPage(page)
    }

    HorizontalPager(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        pageSpacing = 32.dp,
        state = pagerState,
    ) { page ->

        CalendarWeekGroup(
            isWeekEnabled = true,
            dayModels = monthModel.calendarMonth[page],
            onDateSelected = { dayModel ->
                onDateSelect(dayModel)
            },
            selectedDay = selectedDate,
            scrapCount = 0,
        )
    }

}


@Preview
@Composable
private fun CalendarWeekPagerPreview() {
    TerningPointTheme {
        CalendarWeekPager(
            monthModel = TerningCalendarModel().getMonthModelByPage(LocalDate.now()),
            onDateSelect = { },
            selectedDate = DayModel(LocalDate.now())
        )
    }
}

