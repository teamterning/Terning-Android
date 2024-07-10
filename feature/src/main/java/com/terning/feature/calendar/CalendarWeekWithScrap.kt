package com.terning.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.getDateStringInKorean
import com.terning.feature.calendar.models.Scrap
import com.terning.feature.calendar.models.SelectedDateState
import java.time.LocalDate

@Composable
fun CalendarWeekWithScrap(
    modifier: Modifier = Modifier,
    selectedDate: SelectedDateState,
    scrapLists: List<List<Scrap>> = listOf(),
    onDateSelected: (LocalDate) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .border(
                    width = 0.dp,
                    color = Grey200,
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .shadow(
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                    elevation = 1.dp
                ),

            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
        ) {
            CalendarWeek(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White),
                selectedDate = selectedDate,
                onDateSelected = onDateSelected
            )
        }

        Text(
            text = selectedDate.selectedDate.getDateStringInKorean(),
            style = TerningTheme.typography.title5,
            color = Black,
            modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 15.dp)
        )

        if (scrapLists[selectedDate.selectedDate.dayOfMonth - 1].isEmpty()) {
            Text(
                modifier = Modifier
                    .padding(top = 42.dp)
                    .fillMaxWidth(),
                text = "선택하신 날짜에 지원 마감인 스크랩 공고가 없어요.",
                textAlign = TextAlign.Center,
                style = TerningTheme.typography.body5,
                color = Grey400
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                items(items = scrapLists[selectedDate.selectedDate.dayOfMonth - 1]) { scrap ->
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
}



