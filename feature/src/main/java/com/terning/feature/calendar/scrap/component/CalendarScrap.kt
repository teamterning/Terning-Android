package com.terning.feature.calendar.scrap.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.item.InternItem
import com.terning.core.designsystem.component.item.ScrapBox
import com.terning.domain.entity.response.CalendarScrapDetailModel
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

@Composable
fun CalendarScrap(
    scrap: CalendarScrapDetailModel,
    modifier: Modifier = Modifier,
) {
    ScrapBox(
        modifier = modifier,
        cornerRadius = 10.dp,
        scrapColor = Color(android.graphics.Color.parseColor(scrap.color)),
        elevation = 1.dp,
    ) {
        InternItem(
            imageUrl = scrap.companyImage.orEmpty(),
            title = scrap.title,
            dateDeadline = scrap.dDay,
            workingPeriod = scrap.workingPeriod,
            isScraped = scrap.isScrapped
        )
    }
}