package com.terning.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.White
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
                .border(0.dp, Grey200, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ){
            /*items(items = scrapLists[selectedDate?.dayOfMonth - 1]) {

            }*/

        }
    }

}