package com.terning.feature.calendar.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.calendar.models.Scrap
import com.terning.feature.calendar.models.SelectedDateState
import com.terning.feature.calendar.scrap.CalendarScrapList
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
            .background(Back)
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
        CalendarScrapList(
            selectedDate = selectedDate.selectedDate,
            scrapLists = scrapLists,
            noScrapScreen = {
                Text(
                    modifier = Modifier
                        .padding(top = 42.dp)
                        .fillMaxWidth(),
                    text = "선택하신 날짜에 지원 마감인 스크랩 공고가 없어요.",
                    textAlign = TextAlign.Center,
                    style = TerningTheme.typography.body5,
                    color = Grey400
                )
            }
        )
    }
}



