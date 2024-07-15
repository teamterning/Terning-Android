package com.terning.feature.calendar.month

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.designsystem.theme.White
import com.terning.domain.entity.response.ScrapModel
import com.terning.feature.calendar.calendar.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.calendar.CalendarState.Companion.getDateByPage
import com.terning.feature.calendar.calendar.CalendarViewModel
import com.terning.feature.calendar.month.model.MonthData
import com.terning.feature.calendar.scrap.model.Scrap
import com.terning.feature.calendar.calendar.SelectedDateState
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarMonths(
    pages: Int,
    listState: LazyListState,
    selectedDate: SelectedDateState,
    onDateSelected: (LocalDate) -> Unit,
    viewModel: CalendarViewModel,
    modifier: Modifier = Modifier,
) {
    /* var scrapList by remember { mutableStateOf(mockScrapList01) }
       LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                val page = listState.firstVisibleItemIndex

                if(page %2 == 1) {
                    scrapList = mockScrapList01
                } else {
                    scrapList = mockScrapList02
                }
            }
    }*/

    val scrapState by viewModel.scrapList.collectAsState()

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                val page = listState.firstVisibleItemIndex
                val date = getDateByPage(page)
                viewModel.getScrapModelMap(date.year, date.monthValue)
            }
    }

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
            val date = getDateByPage(page)
            val monthData = MonthData(YearMonth.of(date.year, date.month))


            CalendarMonth(
                modifier = Modifier.fillParentMaxSize(),
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                monthData = monthData,
                scrapMap = scrapState
            )
        }
    }
}

//To be erased in future
val mockScrapList01: List<List<Scrap>>
    get() {
        val list: MutableList<List<Scrap>> = mutableListOf()
        for (i in 0..30) {
            when (i % 6) {
                0 -> {
                    list.add(
                        i,
                        listOf()
                    )
                }

                5 -> {
                    list.add(
                        i,
                        listOf(
                            Scrap("Task1_1", CalBlue1, dDay = "1", period = "3", isScraped = true),
                        )
                    )
                }

                2 -> {
                    list.add(
                        i,
                        listOf(
                            Scrap("Task2_1", CalPink, dDay = "2", period = "3", isScraped = true),
                            Scrap("Task2_2", CalGreen1, dDay = "2", period = "3", isScraped = true)
                        )
                    )
                }

                3 -> {
                    list.add(
                        i,
                        listOf()
                    )
                }

                4 -> {
                    list.add(
                        i,
                        listOf()
                    )
                }

                1 -> {
                    list.add(
                        i,
                        listOf(
                            Scrap("Task3_1", CalPink, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_2", CalPurple, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_3", CalRed, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_4", CalBlue1, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_5", CalGreen2, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_6", CalYellow, dDay = "5", period = "3", isScraped = true)
                        )
                    )
                }
            }
        }
        return list.toList()
    }

val mockScrapList02: List<List<Scrap>>
    get() {
        val list: MutableList<List<Scrap>> = mutableListOf()
        for (i in 0..30) {
            when (i % 6) {
                0 -> {
                    list.add(
                        i,
                        listOf()
                    )
                }

                1 -> {
                    list.add(
                        i,
                        listOf(
                            Scrap("Task1_1", CalBlue1, dDay = "1", period = "3", isScraped = true),
                        )
                    )
                }

                2 -> {
                    list.add(
                        i,
                        listOf(
                            Scrap("Task2_1", CalPink, dDay = "2", period = "3", isScraped = true),
                            Scrap("Task2_2", CalGreen1, dDay = "2", period = "3", isScraped = true)
                        )
                    )
                }

                5 -> {
                    list.add(
                        i,
                        listOf()
                    )
                }

                3 -> {
                    list.add(
                        i,
                        listOf()
                    )
                }

                4 -> {
                    list.add(
                        i,
                        listOf(
                            Scrap("Task3_1", CalPink, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_2", CalPurple, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_3", CalRed, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_4", CalBlue1, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_5", CalGreen2, dDay = "5", period = "3", isScraped = true),
                            Scrap("Task3_6", CalYellow, dDay = "5", period = "3", isScraped = true)
                        )
                    )
                }
            }
        }
        return list.toList()
    }