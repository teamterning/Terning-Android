package com.terning.feature.calendar.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.domain.entity.calendar.CalendarScrapDetail
import java.time.LocalDate

@Composable
internal fun CalendarScrapList(
    scrapList: List<CalendarScrapDetail>,
    onScrapButtonClicked: (Long) -> Unit,
    onInternshipClicked: (CalendarScrapDetail) -> Unit,
    modifier: Modifier = Modifier,
    isFromList: Boolean = false
) {
    val scrollState = rememberScrollState()
    val topModifier = modifier.then(
        if (!isFromList) {
            Modifier.verticalScroll(scrollState)
        } else {
            Modifier
        }
    )

    Column(
        modifier = topModifier
    ) {
        for (scrap in scrapList) {
            CalendarScrap(
                scrap = scrap,
                onScrapButtonClicked = onScrapButtonClicked,
                onInternshipClicked = onInternshipClicked
            )
            Spacer(
                modifier = Modifier.height(if(scrap == scrapList.last()) 16.dp else 12.dp)
            )
        }
    }
}