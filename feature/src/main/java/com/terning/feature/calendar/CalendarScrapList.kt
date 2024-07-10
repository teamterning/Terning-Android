package com.terning.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.getDateStringInKorean
import com.terning.feature.calendar.models.Scrap
import java.time.LocalDate

@Composable
fun CalendarScrapList(
    selectedDate: LocalDate,
    scrapLists: List<List<Scrap>>,
    noScrapScreen: @Composable () -> Unit
) {
    Text(
        text = selectedDate.getDateStringInKorean(),
        style = TerningTheme.typography.title5,
        color = Black,
        modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 15.dp)
    )

    if (scrapLists[selectedDate.dayOfMonth - 1].isEmpty()) {
        noScrapScreen()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        ) {
            items(items = scrapLists[selectedDate.dayOfMonth - 1]) { scrap ->
                CalendarScrap(
                    scrap = scrap
                )
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }
    }
}