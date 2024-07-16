package com.terning.feature.calendar.scrap.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.item.InternItem
import com.terning.core.designsystem.component.item.ScrapBox
import com.terning.feature.calendar.scrap.model.Scrap

@Composable
fun CalendarScrap(
    scrap: Scrap,
    modifier: Modifier = Modifier,
) {
    ScrapBox(
        modifier = modifier,
        cornerRadius = 10.dp,
        scrapColor = scrap.backgroundColor,
        elevation = 1.dp,
    ) {
        InternItem(
            imageUrl = scrap.image.orEmpty(),
            title = scrap.text,
            dateDeadline = scrap.dDay,
            workingPeriod = scrap.period,
            isScraped = scrap.isScraped
        )
    }
}