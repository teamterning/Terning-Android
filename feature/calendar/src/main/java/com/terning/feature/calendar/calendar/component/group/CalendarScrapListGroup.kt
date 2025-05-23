package com.terning.feature.calendar.calendar.component.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.domain.calendar.entity.CalendarScrapDetail

@Composable
internal fun CalendarScrapListGroup(
    scrapList: List<CalendarScrapDetail>,
    onScrapButtonClicked: (Long) -> Unit,
    onInternshipClicked: (CalendarScrapDetail) -> Unit,
    modifier: Modifier = Modifier,
    isFromList: Boolean = false
) {
    val scrollState = rememberScrollState()
    val topModifier = modifier.then(
        if (!isFromList) {
            Modifier.verticalScroll(scrollState).padding(top = 15.dp)
        } else {
            Modifier
        }
    )

    Column(
        modifier = topModifier
    ) {
        for (scrap in scrapList) {
            CalendarScrapListItemGroup(
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