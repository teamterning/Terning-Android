package com.terning.feature.calendar.month

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.terning.core.designsystem.theme.White
import com.terning.feature.calendar.calendar.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.calendar.CalendarState.Companion.getDateByPage
import com.terning.feature.calendar.month.model.MonthData
import com.terning.feature.calendar.scrap.model.Scrap
import com.terning.feature.calendar.calendar.SelectedDateState
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonths(
    listState: LazyListState,
    onDateSelected: (LocalDate) -> Unit,
    pages: Int,
    selectedDate: SelectedDateState,
    scrapLists: List<List<Scrap>>,
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

            Log.d("CalendarScreen", "current date : $date")

            CalendarMonth(
                modifier = Modifier.fillParentMaxSize(),
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                monthData = monthData,
                scrapLists = scrapLists
            )
        }
    }
}