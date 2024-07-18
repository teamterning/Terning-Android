package com.terning.feature.calendar.scrap.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.getDateStringInKorean
import com.terning.core.extension.isListNotEmpty
import com.terning.domain.entity.response.CalendarScrapDetailModel
import java.time.LocalDate

@Composable
fun CalendarScrapList(
    selectedDate: LocalDate,
    scrapList: List<CalendarScrapDetailModel>,
    onScrapButtonClicked: (Long) -> Unit,
    onInternshipClicked: (CalendarScrapDetailModel) -> Unit,
    isFromList: Boolean = false
) {
    val scrollState = rememberScrollState()

    if (scrapList.isListNotEmpty()) {
        Text(
            text = selectedDate.getDateStringInKorean(),
            style = TerningTheme.typography.title5,
            color = Black,
            modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 15.dp)
        )
    }
    val topModifier = if (!isFromList) {
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState)
    } else {
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    }
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
                modifier = Modifier.height(12.dp)
            )
        }
    }
}