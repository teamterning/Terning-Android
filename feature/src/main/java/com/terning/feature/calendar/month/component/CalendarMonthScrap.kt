package com.terning.feature.calendar.month.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.domain.entity.calendar.CalendarScrap
import com.terning.feature.R

private const val SCRAP_COUNT_WEEK_SIX = 3
private const val SCRAP_COUNT_WEEK_FIVE = 4

@Composable
internal fun CalendarMonthScrap(
    weekCount: Int,
    modifier: Modifier = Modifier,
    scrapLists: List<CalendarScrap>
) {
    val scrapCount = if(weekCount == 5) SCRAP_COUNT_WEEK_FIVE else SCRAP_COUNT_WEEK_SIX

    LazyColumn(
        modifier = modifier
    ) {
        items(scrapLists.subList(0, scrapCount.coerceAtMost(scrapLists.size))) { scrap ->
            Text(
                text = scrap.title,
                style = TerningTheme.typography.button5,
                textAlign = TextAlign.Center,
                color = White,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .padding(horizontal = 2.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(android.graphics.Color.parseColor(scrap.color)),
                        shape = RoundedCornerShape(size = 1.dp)
                    )
            )
        }

        item {
            if (scrapLists.size > scrapCount) {
                Text(
                    text = stringResource(id = R.string.calendar_scrap_overflow, (scrapLists.size - scrapCount)),
                    style = TerningTheme.typography.detail4,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

