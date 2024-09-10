package com.terning.core.designsystem.component.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.getStringAsTitle
import com.terning.core.extension.noRippleClickable
import java.time.LocalDate
import java.time.YearMonth

/**
 * 달력의 상단바를 표시하기 위한 컴포넌트입니다.
 *
 * @param date 현재 표시되는 연월입니다.
 * @param isWeekExpanded 주간 달력 뷰가 확장되었는지 여부입니다.
 * @param isListExpanded 목록 뷰가 확장되었는지 여부입니다.
 * @param onListButtonClicked 목록 버튼을 클릭했을 때 호출되는 콜백입니다.
 * @param onMonthNavigationButtonClicked 연월을 변경하는 버튼을 클릭했을 때 호출되는 콜백입니다.
 * @param modifier 수정자입니다.
 */

@Composable
fun CalendarTopAppBar(
    date: YearMonth,
    isWeekExpanded: Boolean,
    isListExpanded: Boolean,
    onListButtonClicked: () -> Unit,
    onMonthNavigationButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(
                vertical = 20.dp,
                horizontal = 21.dp
            )
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!isWeekExpanded || isListExpanded) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_previous),
                    contentDescription = stringResource(id = R.string.calendar_button_description_previous),
                    tint = TerningMain,
                    modifier = Modifier.noRippleClickable { onMonthNavigationButtonClicked(-1) }
                )
            }
            Text(
                text = LocalDate.of(date.year, date.month, 1).getStringAsTitle(),
                style = TerningTheme.typography.title2,
                color = Black,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            if(!isWeekExpanded || isListExpanded) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_next),
                    contentDescription = stringResource(id = R.string.calendar_button_description_next),
                    tint = TerningMain,
                    modifier = Modifier.noRippleClickable { onMonthNavigationButtonClicked(1) }
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(
                    id = if (isListExpanded) {
                        R.drawable.ic_calendar_list_selected
                    } else {
                        R.drawable.ic_calendar_list_unselected
                    }
                ),
                contentDescription = stringResource(id = R.string.calendar_button_description_list),
                modifier = Modifier.noRippleClickable { onListButtonClicked() },
                tint = if (isListExpanded) TerningMain else Grey300,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarTopBarPreview() {
    TerningPointTheme {
        CalendarTopAppBar(
            date = YearMonth.now(),
            isListExpanded = false,
            isWeekExpanded = false
            ,
            onListButtonClicked = {},
            onMonthNavigationButtonClicked = {}
        )
    }
}