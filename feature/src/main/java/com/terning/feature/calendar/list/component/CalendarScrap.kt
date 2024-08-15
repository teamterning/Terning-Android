package com.terning.feature.calendar.list.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.item.InternItem
import com.terning.core.designsystem.component.item.ScrapBox
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.response.CalendarScrapDetailModel

@Composable
fun CalendarScrap(
    scrap: CalendarScrapDetailModel,
    onScrapButtonClicked: (Long) -> Unit,
    onInternshipClicked: (CalendarScrapDetailModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    ScrapBox(
        modifier = modifier.noRippleClickable {
            onInternshipClicked(scrap)
        },
        cornerRadius = 10.dp,
        scrapColor = Color(android.graphics.Color.parseColor(scrap.color)),
        elevation = 1.dp,
    ) {
        InternItem(
            scrapId = scrap.scrapId,
            imageUrl = scrap.companyImage.orEmpty(),
            title = scrap.title,
            dateDeadline = scrap.dDay,
            workingPeriod = scrap.workingPeriod,
            isScraped = scrap.isScrapped,
            onScrapButtonClicked = onScrapButtonClicked
        )
    }
}