package com.terning.feature.calendar.week

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.R
import com.terning.feature.calendar.calendar.CalendarUiState
import com.terning.feature.calendar.calendar.CalendarViewModel
import com.terning.feature.calendar.scrap.model.Scrap
import com.terning.feature.calendar.scrap.CalendarScrapListss
import java.time.LocalDate

@Composable
fun CalendarWeekScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState = viewModel.selectedDate.collectAsStateWithLifecycle(lifecycleOwner)

}

@Composable
fun CalendarWeekWithScrap(
    modifier: Modifier = Modifier,
    selectedDate: CalendarUiState,
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
        CalendarScrapListss(
            selectedDate = selectedDate.selectedDate,
            scrapLists = scrapLists,
            noScrapScreen = {
                Text(
                    modifier = Modifier
                        .padding(top = 42.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.calendar_empty_scrap),
                    textAlign = TextAlign.Center,
                    style = TerningTheme.typography.body5,
                    color = Grey400
                )
            }
        )
    }
}



