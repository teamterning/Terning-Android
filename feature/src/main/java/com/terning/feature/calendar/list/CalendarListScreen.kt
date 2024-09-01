package com.terning.feature.calendar.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.getDateAsMapString
import com.terning.core.extension.getFullDateStringInKorean
import com.terning.core.extension.isListNotEmpty
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.CalendarScrapDetail
import com.terning.feature.R
import com.terning.feature.calendar.calendar.model.CalendarDefaults.flingBehavior
import com.terning.feature.calendar.calendar.model.CalendarModel.Companion.getLocalDateByPage
import com.terning.feature.calendar.list.component.CalendarScrapList
import com.terning.feature.calendar.list.model.CalendarListUiState
import com.terning.feature.dialog.cancel.ScrapCancelDialog
import com.terning.feature.dialog.detail.ScrapDialog
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDate

@Composable
fun CalendarListRoute(
    pages: Int,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    navigateToAnnouncement: (Long) -> Unit,
    viewModel: CalendarListViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner)

    val context = LocalContext.current
    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is CalendarListSideEffect.ShowToast -> context.toast(sideEffect.message)
                }
            }
    }

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect {
                val page = listState.firstVisibleItemIndex
                val date = getLocalDateByPage(page)
                viewModel.updateCurrentDate(date)
                viewModel.getScrapMonthList(date)
            }
    }

    CalendarListScreen(
        pages = pages,
        listState = listState,
        uiState = uiState,
        modifier = modifier,
        navigateToAnnouncement = { announcementId ->
            navigateToAnnouncement(announcementId)
            viewModel.updateInternDialogVisibility(false)
        },
        onDismissCancelDialog = { isCancelled ->
            viewModel.updateScrapCancelDialogVisibility(false)
            if (isCancelled) { viewModel.getScrapMonthList(uiState.currentDate) }
        },
        onDismissInternDialog = { viewModel.updateInternDialogVisibility(false) },
        onClickChangeColor = {
            viewModel.getScrapMonthList(uiState.currentDate)
        },
        onClickScrapButton = { scrapId ->
            with(viewModel){
                updateScrapId(scrapId)
                updateScrapCancelDialogVisibility(true)
            }
        },
        onClickInternship = { calendarScrapDetail ->
            with(viewModel) {
                updateInternshipModel(calendarScrapDetail)
                updateInternDialogVisibility(true)
            }
        }
    )
}

@Composable
private fun CalendarListScreen(
    pages: Int,
    listState: LazyListState,
    uiState: CalendarListUiState,
    navigateToAnnouncement: (Long) -> Unit,
    onDismissCancelDialog: (Boolean) -> Unit,
    onDismissInternDialog: () -> Unit,
    onClickChangeColor: () -> Unit,
    onClickInternship: (CalendarScrapDetail) -> Unit,
    onClickScrapButton: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
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
                val getDate = getLocalDateByPage(page)

                LazyColumn(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .fillMaxHeight()
                        .background(Back)
                ) {
                    when (uiState.loadState) {
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
                                val scrapMap = uiState.loadState.data
                                val currentDate =
                                    LocalDate.of(getDate.year, getDate.monthValue, day + 1)
                                val dateIndex = currentDate.getDateAsMapString()

                                if (scrapMap[dateIndex].isListNotEmpty()) {
                                    CalendarScrapList(
                                        selectedDate = currentDate,
                                        scrapList = scrapMap[dateIndex].orEmpty(),
                                        onScrapButtonClicked = onClickScrapButton,
                                        onInternshipClicked = onClickInternship,
                                        isFromList = true
                                    )

                                    Spacer(
                                        modifier = Modifier
                                            .padding(top = 4.dp)
                                            .fillMaxWidth()
                                            .height(4.dp)
                                            .background(Grey200)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (uiState.scrapDialogVisibility) {
        uiState.scrapId?.run {
            ScrapCancelDialog(
                scrapId = this,
                onDismissRequest = onDismissCancelDialog
            )
        }
    }

    if (uiState.internDialogVisibility) {
        uiState.internshipModel?.let {
            val scrapColor = Color(
                android.graphics.Color.parseColor(
                    uiState.internshipModel.color
                )
            )
            ScrapDialog(
                title = uiState.internshipModel.title,
                scrapColor = scrapColor,
                deadline = uiState.currentDate.getFullDateStringInKorean(),
                startYear = uiState.internshipModel.startYear,
                startMonth = uiState.internshipModel.startMonth,
                workingPeriod = uiState.internshipModel.workingPeriod,
                scrapId = uiState.internshipModel.scrapId,
                internshipAnnouncementId = uiState.internshipModel.internshipAnnouncementId,
                companyImage = uiState.internshipModel.companyImage,
                isScrapped = true,
                onDismissRequest = onDismissInternDialog,
                onClickChangeColor = onClickChangeColor,
                onClickNavigateButton = navigateToAnnouncement
            )
        }
    }
}


