package com.terning.feature.calendar.month

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.extension.getDateAsMapString
import com.terning.core.extension.isToday
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.response.CalendarScrapModel
import com.terning.feature.calendar.calendar.CalendarUiState
import com.terning.feature.calendar.month.component.CalendarDay
import com.terning.feature.calendar.month.model.MonthData
import com.terning.feature.calendar.scrap.CalendarMonthScrap
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonth(
    modifier: Modifier = Modifier,
    monthData: MonthData,
    onDateSelected: (LocalDate) -> Unit,
    calendarUiState: CalendarUiState,
    scrapMap: Map<String, List<CalendarScrapModel>> = mapOf()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        val month = monthData.calendarMonth.weekDays
        for (week in month) {
            Row(
                modifier = Modifier.weight(1f),
            ) {
                for (day in week) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .noRippleClickable {
                                if(!day.isOutDate) {
                                    onDateSelected(day.date)
                                }
                            }
                            .padding(top = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CalendarDay(
                            dayData = day,
                            isSelected = calendarUiState.selectedDate == day.date && calendarUiState.isWeekEnabled,
                            isToday = day.date.isToday(),
                            onDateSelected = onDateSelected
                        )
                        if (!day.isOutDate) {
                            val index = day.date.getDateAsMapString()
                            CalendarMonthScrap(
                                scrapLists = scrapMap[index].orEmpty()
                            )
                        }
                    }
                }
            }
            if (month.indexOf(week) != month.lastIndex) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Grey150
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarMonthPreview() {
    TerningPointTheme {
        CalendarMonth(
            monthData = MonthData(YearMonth.now()),
            calendarUiState = CalendarUiState(LocalDate.now(), true),
            onDateSelected = {},
        )
    }
}