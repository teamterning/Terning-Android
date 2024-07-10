package com.terning.feature.calendar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.item.InternItem
import com.terning.core.designsystem.component.item.ScrapBox
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.feature.calendar.models.Scrap

@Composable
fun CalendarScrap(
    modifier: Modifier = Modifier,
    scrap: Scrap
) {
    ScrapBox(
        cornerRadius = 10.dp,
        scrapColor = scrap.backgroundColor,
        modifier = modifier
            .height(height = 92.dp)
            .fillMaxWidth(),
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

@Preview(showBackground = true)
@Composable
fun CalendarScrapPreview() {
    TerningPointTheme {
        CalendarScrap(
            scrap = Scrap(
                text = "Task1_1",
                backgroundColor = CalBlue1,
                dDay = "1",
                period = "3",
                isScraped = true
            )
        )
    }
}