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

@Composable
fun CalendarTopBar(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isWeekExpanded: Boolean,
    isListExpanded: Boolean,
    onListButtonClicked: () -> Unit,
    onMonthNavigationButtonClicked: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(
                top = 23.dp,
                bottom = 22.dp,
                start = 22.dp,
                end = 22.dp
            )
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!isWeekExpanded) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_previous),
                    contentDescription = stringResource(id = R.string.calendar_button_description_previous),
                    tint = Grey300,
                    modifier = Modifier.noRippleClickable { onMonthNavigationButtonClicked(-1) }
                )
            }
            Text(
                text = date.getStringAsTitle(),
                style = TerningTheme.typography.title2,
                color = Black,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            if(!isWeekExpanded) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_next),
                    contentDescription = stringResource(id = R.string.calendar_button_description_next),
                    tint = Grey300,
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
        CalendarTopBar(
            date = LocalDate.now(),
            isListExpanded = false,
            isWeekExpanded = false
            ,
            onListButtonClicked = {},
            onMonthNavigationButtonClicked = {}
        )
    }
}