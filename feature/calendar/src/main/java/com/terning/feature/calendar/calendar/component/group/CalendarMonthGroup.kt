package com.terning.feature.calendar.calendar.component.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.domain.calendar.entity.CalendarScrap
import com.terning.feature.calendar.calendar.model.DayModel
import com.terning.feature.calendar.calendar.model.MonthModel
import java.time.LocalDate
import java.time.YearMonth

/**
 * 한 달을 표시하는 컴포넌트
 *
 * @param dayModels 한달에 포함된 날들에 대한 데이터 리스트
 * @param isWeekEnabled 주간 달력일 때만 선택된 날에 초록색 마크를 달기 위해 사용하는 부울 변수.
 * @param onDateSelected 날이 선택돼었을 때 발생하는 이벤트
 * @param selectedDate 선택된 날
 * @param modifier 수정자
 * @param scrapMap 스크랩 목록과 날짜가 매핑된 목록
 *
 */

@Composable
internal fun CalendarMonthGroup(
    dayModels: List<List<DayModel>>,
    isWeekEnabled: Boolean,
    onDateSelected: (DayModel) -> Unit,
    selectedDate: DayModel,
    modifier: Modifier = Modifier,
    scrapMap: Map<String, List<CalendarScrap>> = emptyMap()
) {
    val scrapCount = remember(dayModels) {
        if (dayModels.size == 5) SCRAP_COUNT_WEEK_FIVE
        else SCRAP_COUNT_WEEK_SIX
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 20.dp)
    ) {
        dayModels.forEach {week ->
            CalendarWeekGroup(
                dayModels = week,
                isWeekEnabled = isWeekEnabled,
                onDateSelected = onDateSelected,
                selectedDay = selectedDate,
                scrapCount = scrapCount,
                scrapMap = scrapMap,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            )

            if (dayModels.indexOf(week) != dayModels.lastIndex) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Grey150,
                )
            }
        }
    }
}


private const val SCRAP_COUNT_WEEK_SIX = 3
private const val SCRAP_COUNT_WEEK_FIVE = 4

@Preview(showSystemUi = true)
@Composable
private fun CalendarMonthGroupPreview() {
    TerningPointTheme {
        val monthModel = MonthModel(YearMonth.now())

        CalendarMonthGroup(
            dayModels = monthModel.calendarMonth,
            onDateSelected = {},
            selectedDate = DayModel(
                date = LocalDate.now(),
                weekIndex = 1,
                isOutDate = false
            ),
            scrapMap = mapOf(
                "2024-12-11" to listOf(
                    CalendarScrap(
                        scrapId = 1,
                        title = "테스트1",
                        deadLine = "2024-12-11",
                        color = "#FF0F0F",
                        isScrapped = true
                    )
                )
            ),
            isWeekEnabled = false,
        )
    }
}