package com.terning.feature.calendar

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
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R

@Composable
fun CalendarTopBar(
    modifier: Modifier = Modifier,
    title: String,
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
                end = 22.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar_previous),
                contentDescription = stringResource(id = R.string.calendar_button_description_previous),
                tint = Grey300,
                modifier = Modifier.noRippleClickable { onMonthNavigationButtonClicked(-1) }
            )
            Text(
                text = title,
                style = TerningTheme.typography.title2,
                color = Black,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar_next),
                contentDescription = stringResource(id = R.string.calendar_button_description_next),
                tint = Grey300,
                modifier = Modifier.noRippleClickable { onMonthNavigationButtonClicked(1) }
            )
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
                tint = if (isListExpanded) Color.Green else Color.LightGray,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarTopBarPreview() {
    TerningTheme {
        CalendarTopBar(
            title = "2024년 7월",
            isListExpanded = false,
            onListButtonClicked = {},
            onMonthNavigationButtonClicked = {}
        )
    }
}