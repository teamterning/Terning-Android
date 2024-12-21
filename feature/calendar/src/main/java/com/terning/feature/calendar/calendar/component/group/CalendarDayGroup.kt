package com.terning.feature.calendar.calendar.component.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.calendar.calendar.model.DayModel
import java.time.LocalDate

/**
 * 날짜를 표시하는 컴포넌트 그룹
 *
 * @param dayModel 날짜에 대한 정보
 * @param isSelected 해당 날짜가 선택된 날짜인지에 대한 여부
 * @param isToday 해당 날짜가 오늘 날짜인지에 대한 여부
 * @param modifier 수정자
 */

@Composable
fun CalendarDayGroup(
    dayModel: DayModel,
    isSelected: Boolean,
    isToday: Boolean,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = remember(isSelected) {
        if (isSelected) TerningMain
        else if (isToday) Grey150
        else Color.Transparent
    }

    val textColor = remember(isSelected) {
        if (dayModel.isOutDate) Grey200
        else if (isSelected) White
        else Black
    }


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayModel.date.dayOfMonth.toString(),
                color = textColor,
                style = TerningTheme.typography.calendar
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarDayPreview() {
    TerningPointTheme {
        Row {
            CalendarDayGroup(
                dayModel = DayModel(LocalDate.now(), 0, false),
                isSelected = true,
                isToday = true,
            )
            CalendarDayGroup(
                dayModel = DayModel(LocalDate.now(), 0, false),
                isSelected = false,
                isToday = true,
            )
            CalendarDayGroup(
                dayModel = DayModel(LocalDate.now(), 0, false),
                isSelected = false,
                isToday = false,
            )
        }
    }
}