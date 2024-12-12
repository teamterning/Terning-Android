package com.terning.feature.calendar.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.extension.getFullDateStringInKorean
import com.terning.core.designsystem.extension.isListNotEmpty
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.domain.calendar.entity.CalendarScrapDetail
import com.terning.feature.calendar.R
import com.terning.feature.calendar.calendar.component.dialog.CalendarScrapCancelDialog
import com.terning.feature.calendar.calendar.component.dialog.CalendarScrapPatchDialog
import com.terning.feature.calendar.calendar.component.group.CalendarScrapListGroup
import com.terning.feature.calendar.calendar.model.TerningCalendarModel
import com.terning.feature.calendar.list.model.CalendarListUiState
import okhttp3.internal.toImmutableList
import java.time.LocalDate

@Composable
fun CalendarListRoute(
    modifier: Modifier = Modifier,
    calendarModel: TerningCalendarModel,
    pagerState: PagerState,
    navigateUp: () -> Unit,
    navigateToAnnouncement: (Long) -> Unit,
    viewModel: CalendarListViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is CalendarListSideEffect.ShowToast -> context.toast(sideEffect.message)
                }
            }
    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.settledPage }
            .collect { settled ->
                val date = calendarModel.getLocalDateByPage(settled)
                viewModel.updateCurrentDate(date)
                viewModel.getScrapMonthList(date)
            }
    }

    BackHandler {
        navigateUp()
    }

    CalendarListScreen(
        pagerState = pagerState,
        uiState = uiState,
        calendarModel = calendarModel,
        modifier = modifier,
        onClickScrapButton = { scrapId ->
            with(viewModel) {
                updateAnnouncementId(scrapId)
                updateScrapCancelDialogVisibility(true)
            }
        },
        onClickInternship = { calendarScrapDetail ->
            with(viewModel) {
                updateInternshipModel(calendarScrapDetail)
                updateScrapDetailDialogVisibility(true)
            }
        }
    )

    CalendarScrapPatchDialog(
        date = uiState.currentDate,
        dialogVisibility = uiState.scrapDetailDialogVisibility,
        internshipModel = uiState.internshipModel,
        navigateToAnnouncement = { announcementId ->
            navigateToAnnouncement(announcementId)
            viewModel.updateScrapDetailDialogVisibility(false)
        },
        onDismissInternDialog = { viewModel.updateScrapDetailDialogVisibility(false) },
        onClickChangeColor = {
            viewModel.getScrapMonthList(uiState.currentDate)
        },
    )

    CalendarScrapCancelDialog(
        scrapVisibility = uiState.scrapCancelDialogVisibility,
        internshipAnnouncementId = uiState.internshipAnnouncementId,
        onDismissCancelDialog = { isCancelled ->
            viewModel.updateScrapCancelDialogVisibility(false)
            if (isCancelled) {
                viewModel.getScrapMonthList(uiState.currentDate)
            }
        }
    )
}

@Composable
private fun CalendarListScreen(
    pagerState: PagerState,
    calendarModel: TerningCalendarModel,
    uiState: CalendarListUiState,
    onClickInternship: (CalendarScrapDetail) -> Unit,
    onClickScrapButton: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {

    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { page ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .background(Back)
        ) {
            when (uiState.loadState) {
                UiState.Loading -> {}
                UiState.Empty -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CalendarListEmpty()
                        }
                    }
                }

                is UiState.Failure -> {}
                is UiState.Success -> {
                    val date = calendarModel.getLocalDateByPage(page)

                    items(date.lengthOfMonth()) { day ->
                        CalendarListSuccess(
                            date = LocalDate.of(date.year, date.monthValue, day + 1),
                            scrapMap = uiState.loadState.data,
                            onClickScrapButton = onClickScrapButton,
                            onClickInternship = onClickInternship,
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun CalendarListEmpty(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(
            id = R.drawable.ic_terning_calendar_empty
        ),
        contentDescription = "",
        modifier = modifier.padding(top = 20.dp, bottom = 4.dp)
    )

    Text(
        modifier = modifier.fillMaxWidth(),
        text = stringResource(id = R.string.calendar_empty_scrap),
        textAlign = TextAlign.Center,
        style = TerningTheme.typography.body5,
        color = Grey400
    )
}

@Composable
private fun CalendarListSuccess(
    date: LocalDate,
    scrapMap: Map<String, List<CalendarScrapDetail>>,
    onClickScrapButton: (Long) -> Unit,
    onClickInternship: (CalendarScrapDetail) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dateInKorean = date.getFullDateStringInKorean()

    if (scrapMap[dateInKorean].isListNotEmpty()) {
        Text(
            text = dateInKorean,
            style = TerningTheme.typography.title5,
            color = Black,
            modifier = modifier
                .padding(start = 24.dp, top = 16.dp, bottom = 15.dp)
        )

        CalendarScrapListGroup(
            scrapList = scrapMap[dateInKorean].orEmpty().toImmutableList(),
            onScrapButtonClicked = onClickScrapButton,
            onInternshipClicked = onClickInternship,
            isFromList = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}
