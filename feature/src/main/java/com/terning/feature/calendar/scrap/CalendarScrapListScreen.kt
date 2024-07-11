package com.terning.feature.calendar.scrap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.White
import com.terning.feature.calendar.models.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.models.CalendarState.Companion.getDateByPage
import com.terning.feature.calendar.models.Scrap
import java.time.LocalDate

@Composable
fun CalendarScrapListScreen(
    date: LocalDate,
    scrapList: List<List<Scrap>>,
    pages: Int,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .background(White),
        state = listState,
        userScrollEnabled = true,
        flingBehavior = flingBehavior(
            state = listState
        )
    ) {
        items(pages) { page ->
            val getDate = getDateByPage(page)

            LazyColumn(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .fillMaxHeight()
                    .background(Back)
            ) {
                items(scrapList.size) { day ->
                    val currentDate = LocalDate.of(getDate.year, getDate.monthValue, day + 1)
                    CalendarScrapList(
                        selectedDate = currentDate,
                        scrapLists = scrapList,
                        isFromList = true,
                        noScrapScreen = {})


                    if (scrapList[day].isNotEmpty()) {
                        Spacer(
                            modifier = Modifier
                                .height(4.dp)
                                .fillMaxWidth()
                                .background(Grey200)
                        )
                    }
                }
            }
        }
    }

}

