package com.terning.feature.calendar.month.component

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.terning.core.designsystem.theme.White
import com.terning.domain.entity.response.CalendarScrapModel
import com.terning.feature.calendar.calendar.model.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getDateByPage
import com.terning.feature.calendar.month.model.MonthData
import java.time.LocalDate
import java.time.YearMonth

@Composable
internal fun HorizontalCalendar(
    pages: Int,
    listState: LazyListState,
    isWeekEnabled: Boolean,
    selectedDate: LocalDate,
    scrapMap: Map<String, List<CalendarScrapModel>>,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .background(White),
        state = listState,
        userScrollEnabled = true,
        flingBehavior = flingBehavior(
            state = listState
        )
    ) {
        items(pages) { page ->
            val date = getDateByPage(page)
            val monthData = MonthData(YearMonth.of(date.year, date.month))

            CalendarMonth(
                modifier = Modifier.fillParentMaxSize(),
                onDateSelected = onDateSelected,
                monthData = monthData,
                scrapMap = scrapMap,
                selectedDate = selectedDate,
                isWeekEnabled = isWeekEnabled
            )
        }
    }
}