package com.terning.feature.calendar.calendar.component.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.domain.calendar.entity.CalendarScrap
import com.terning.feature.calendar.calendar.model.DayModel
import java.time.LocalDate

/**
 * 일주일을 표시하기 위한 컴포넌트
 *
 * @param selectedDay 현재 선택된 날짜
 * @param dayModels 해당 주에 포함된 날짜들에 대한 DayModel 목록
 * @param onDateSelected 날짜를 선택했을 때 호출되는 콜백
 * @param scrapCount 표시 가능한 스크랩 개수
 * @param scrapMap 날짜-스크랩 쌍을 담은 맵
 */

@Composable
internal fun CalendarWeekGroup(
    dayModels: List<DayModel>,
    isWeekEnabled: Boolean,
    onDateSelected: (DayModel) -> Unit,
    scrapCount: Int,
    selectedDay: DayModel,
    modifier: Modifier = Modifier,
    scrapMap: Map<String, List<CalendarScrap>> = emptyMap()
) {
    Row(
        modifier = modifier
    ){
        dayModels.forEach { dayModel ->
            Column(
                modifier = (Modifier.fillMaxHeight().takeIf { !isWeekEnabled } ?: Modifier)
                    .weight(1f)
                    .noRippleClickable {
                        if (!dayModel.isOutDate) {
                            onDateSelected(dayModel)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CalendarDayGroup(
                    dayModel = dayModel,
                    isSelected = dayModel == selectedDay && isWeekEnabled,
                    isToday = dayModel.isToday(),
                )

                if (!dayModel.isOutDate && !isWeekEnabled) {
                    val scrapMapKey = dayModel.getScrapMapKey()
                    CalendarScrapGroup(
                        scrapCount = scrapCount,
                        scrapLists = scrapMap[scrapMapKey].orEmpty(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CalendarWeekGroupNoScrapPreview() {
    TerningPointTheme {
        var selectedDay by remember { mutableStateOf(DayModel(date = LocalDate.now(), weekIndex = 0, isOutDate = false)) }

        CalendarWeekGroup(
            selectedDay = selectedDay,
            isWeekEnabled = false,
            dayModels = listOf(
                DayModel(date = LocalDate.now().minusDays(3), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().minusDays(2), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().minusDays(1), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now(), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().plusDays(1), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().plusDays(2), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().plusDays(3), weekIndex = 0, isOutDate = false)
            ),
            onDateSelected = {
                selectedDay = it
            },
            scrapCount = 3,
            modifier = Modifier.fillMaxWidth().wrapContentHeight().background(White)
        )
    }
}

@Preview
@Composable
private fun CalendarWeekGroupScrapPreview() {
    TerningPointTheme {

        CalendarWeekGroup(
            selectedDay = DayModel(date = LocalDate.now().minusDays(1), weekIndex = 0, isOutDate = false),
            isWeekEnabled = true,
            dayModels = listOf(
                DayModel(date = LocalDate.now().minusDays(3), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().minusDays(2), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().minusDays(1), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now(), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().plusDays(1), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().plusDays(2), weekIndex = 0, isOutDate = false),
                DayModel(date = LocalDate.now().plusDays(3), weekIndex = 0, isOutDate = false)
            ),
            onDateSelected = {},
            scrapCount = 3,
            modifier = Modifier.fillMaxWidth().wrapContentHeight().background(White),
            scrapMap = mapOf(
                "2024-12-10" to listOf(
                    CalendarScrap(
                        scrapId = 1,
                        title = "테스트1",
                        deadLine = "2024-12-10",
                        color = "#FF0F0F",
                        isScrapped = true
                    ),
                    CalendarScrap(
                        scrapId = 1,
                        title = "테스트2",
                        deadLine = "2024-12-10",
                        color = "#00FF00",
                        isScrapped = true
                    ),
                    CalendarScrap(
                        scrapId = 1,
                        title = "테스트3",
                        deadLine = "2024-12-10",
                        color = "#0000FF",
                        isScrapped = true
                    ),
                    CalendarScrap(
                        scrapId = 1,
                        title = "테스트4",
                        deadLine = "2024-12-10",
                        color = "#FF00FF",
                        isScrapped = true
                    )
                )
            )
        )
    }
}