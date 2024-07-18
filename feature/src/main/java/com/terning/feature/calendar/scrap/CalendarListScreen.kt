package com.terning.feature.calendar.scrap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.getDateAsMapString
import com.terning.core.extension.isListNotEmpty
import com.terning.core.state.UiState
import com.terning.feature.R
import com.terning.feature.calendar.calendar.CalendarUiState
import com.terning.feature.calendar.calendar.CalendarViewModel
import com.terning.feature.calendar.calendar.component.InternDetailDialog
import com.terning.feature.calendar.calendar.component.ScrapCancelDialog
import com.terning.feature.calendar.calendar.model.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.calendar.model.CalendarState.Companion.getDateByPage
import com.terning.feature.calendar.scrap.component.CalendarScrapList
import kotlinx.coroutines.flow.distinctUntilChanged
import timber.log.Timber
import java.time.LocalDate

@Composable
fun CalendarListScreen(
    pages: Int,
    listState: LazyListState,
    uiState: CalendarUiState,
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrapState by viewModel.calendarListState.collectAsStateWithLifecycle(lifecycleOwner)

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                val page = listState.firstVisibleItemIndex
                val date = getDateByPage(page)
                viewModel.getScrapMonthList(date.year, date.monthValue)
            }
    }

    Box {
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
                    when (scrapState.loadState) {
                        UiState.Loading -> {}
                        UiState.Empty -> {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
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
                            }
                        }

                        is UiState.Failure -> {}
                        is UiState.Success -> {
                            items(getDate.lengthOfMonth()) { day ->
                                val scrapMap = (scrapState.loadState as UiState.Success).data
                                val currentDate =
                                    LocalDate.of(getDate.year, getDate.monthValue, day + 1)
                                val dateIndex = currentDate.getDateAsMapString()

                                if (scrapMap[dateIndex].isListNotEmpty()) {
                                    CalendarScrapList(
                                        selectedDate = currentDate,
                                        scrapList = scrapMap[dateIndex].orEmpty(),
                                        onScrapButtonClicked = { scrapId ->
                                            viewModel.updateScrapCancelDialogVisible(scrapId)
                                        },
                                        onInternshipClicked = { internshipAnnouncementId ->
                                            viewModel.updateInternDialogVisible(
                                                internshipAnnouncementId
                                            )
                                        },
                                        isFromList = true,
                                        noScrapScreen = {})
                                }
                            }
                        }
                    }
                }
            }
        }
        if (uiState.isScrapButtonClicked) {
            ScrapCancelDialog(
                onDismissRequest = { viewModel.updateScrapCancelDialogVisible() },
                onClickScrapCancel = {
                    viewModel.updateScrapCancelDialogVisible()
                }
            )
        }
        if (uiState.isInternshipClicked) {
            InternDetailDialog(
                onDismissRequest = {viewModel.updateInternDialogVisible(null)},
                onClickColor = { newColor ->
                    Timber.tag("CalendarScreen")
                        .d("<CalendarListScreen>: $newColor")
                },
                onClickNavigate = {
                    viewModel.updateInternDialogVisible(null)
                }
            )
        }
    }
}


