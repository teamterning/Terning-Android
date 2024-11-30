package com.terning.feature.calendar.month.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.extension.getDateAsMapString
import com.terning.core.designsystem.extension.isToday
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.domain.calendar.entity.CalendarScrap
import com.terning.feature.calendar.calendar.component.CalendarDay
import com.terning.feature.calendar.month.model.MonthModel
import java.time.LocalDate
import java.time.YearMonth

@Composable
internal fun CalendarMonth(
    isWeekEnabled: Boolean,
    monthModel: MonthModel,
    onDateSelected: (LocalDate) -> Unit,
    selectedDate: LocalDate,
    modifier: Modifier = Modifier,
    scrapMap: Map<String, List<CalendarScrap>> = mapOf()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        val month = monthModel.calendarMonth.weekDays
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
                            isSelected = selectedDate == day.date && isWeekEnabled,
                            isToday = day.date.isToday(),
                            onDateSelected = onDateSelected
                        )
                        if (!day.isOutDate) {
                            val index = day.date.getDateAsMapString()
                            CalendarMonthScrap(
                                weekCount = month.size,
                                scrapLists = scrapMap[index].orEmpty(),
                                modifier = Modifier.fillMaxWidth()
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
private fun CalendarMonthPreview() {
    TerningPointTheme {
        CalendarMonth(
            monthModel = MonthModel(YearMonth.now()),
            onDateSelected = {},
            selectedDate = LocalDate.now(),
            isWeekEnabled = true
        )
    }
}