package com.terning.feature.calendar.calendar.component.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.terning.domain.calendar.entity.CalendarScrap
import com.terning.feature.calendar.R

/**
 * 달력 위에 띠지를 표시하는 컴포넌트
 *
 * @param scrapCount 한칸에 표시 가능한 최대 스크랩
 * @param scrapLists 스크랩 목록
 */

@Composable
internal fun CalendarScrapGroup(
    scrapCount: Int,
    scrapLists: List<CalendarScrap>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        scrapLists.subList(0, scrapCount.coerceAtMost(scrapLists.size)).forEach { scrap ->
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
        if (scrapLists.size > scrapCount) {
            Text(
                text = stringResource(
                    id = R.string.calendar_scrap_overflow,
                    (scrapLists.size - scrapCount)
                ),
                style = TerningTheme.typography.detail4,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
            )
        }
    }
}