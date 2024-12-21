package com.terning.feature.calendar.week

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.extension.getDateStringInKorean
import com.terning.core.designsystem.extension.swipableVertically
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.domain.calendar.entity.CalendarScrapDetail
import com.terning.feature.calendar.R
import com.terning.feature.calendar.calendar.component.dialog.CalendarScrapCancelDialog
import com.terning.feature.calendar.calendar.component.dialog.CalendarScrapPatchDialog
import com.terning.feature.calendar.calendar.component.group.CalendarScrapListGroup
import com.terning.feature.calendar.calendar.component.pager.CalendarWeekPager
import com.terning.feature.calendar.calendar.model.DayModel
import com.terning.feature.calendar.calendar.model.TerningCalendarModel
import com.terning.feature.calendar.week.model.CalendarWeekUiState
import okhttp3.internal.toImmutableList

@Composable
fun CalendarWeekRoute(
    selectedDate: DayModel,
    calendarModel: TerningCalendarModel,
    pagerState: PagerState,
    navigateToAnnouncement: (Long) -> Unit,
    updateSelectedDate: (DayModel) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalendarWeekViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner)

    val context = LocalContext.current
    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is CalendarWeekSideEffect.ShowToast -> context.toast(sideEffect.message)
                }
            }
    }

    LaunchedEffect(key1 = selectedDate) {
        viewModel.getScrapWeekList(selectedDate = selectedDate.date)
    }

    BackHandler {
        navigateUp()
    }

    CalendarWeekScreen(
        modifier = modifier.fillMaxSize(),
        pagerState = pagerState,
        calendarModel = calendarModel,
        uiState = uiState,
        selectedDate = selectedDate,
        updateSelectedDate = updateSelectedDate,
        onClickScrapButton = { scrapId ->
            with(viewModel) {
                updateInternshipAnnouncementId(scrapId)
                updateScrapCancelDialogVisibility(true)
            }
        },
        onClickInternship = { scrapDetail ->
            with(viewModel) {
                updateScrapDetailDialogVisibility(true)
                updateInternshipModel(scrapDetail)
            }
        },
    )

    CalendarScrapPatchDialog(
        date = selectedDate.date,
        dialogVisibility = uiState.scrapDetailDialogVisibility,
        internshipModel = uiState.internshipModel,
        navigateToAnnouncement = { announcementId ->
            navigateToAnnouncement(announcementId)
            viewModel.updateScrapDetailDialogVisibility(false)
        },
        onDismissInternDialog = { viewModel.updateScrapDetailDialogVisibility(false) },
        onClickChangeColor = {
            viewModel.getScrapWeekList(selectedDate.date)
        },
    )

    CalendarScrapCancelDialog(
        scrapVisibility = uiState.scrapCancelDialogVisibility,
        internshipAnnouncementId = uiState.internshipAnnouncementId,
        onDismissCancelDialog = { isCancelled ->
            viewModel.updateScrapCancelDialogVisibility(false)
            if (isCancelled) {
                viewModel.getScrapWeekList(selectedDate.date)
            }
        }
    )
}

@Composable
private fun CalendarWeekScreen(
    uiState: CalendarWeekUiState,
    calendarModel: TerningCalendarModel,
    pagerState: PagerState,
    selectedDate: DayModel,
    updateSelectedDate: (DayModel) -> Unit,
    onClickInternship: (CalendarScrapDetail) -> Unit,
    onClickScrapButton: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var swiped by remember { mutableStateOf(false) }

    LaunchedEffect(swiped) {
        if (swiped) {
            updateSelectedDate(selectedDate)
        }
    }

    Column(
        modifier = modifier
            .background(Back),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .swipableVertically { swiped = true }
                .shadow(
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                    elevation = 1.dp
                ),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
        ) {
            CalendarWeekPager(
                monthModel = calendarModel.getMonthModelByPage(selectedDate.date),
                selectedDate = selectedDate,
                onDateSelect = updateSelectedDate,
            )

            Spacer(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp)
                    .size(width = 55.dp, height = 4.dp)
                    .background(color = Grey200, shape = RoundedCornerShape(2.dp))
                    .align(Alignment.CenterHorizontally),
            )
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = selectedDate.date.getDateStringInKorean(),
                    style = TerningTheme.typography.title5,
                    color = Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 16.dp)
                )

                when (uiState.loadState) {
                    is UiState.Loading -> {}
                    is UiState.Empty -> {
                        CalendarWeekEmpty()
                    }

                    is UiState.Failure -> {
                        CalendarWeekEmpty()
                    }

                    is UiState.Success -> {
                        CalendarWeekSuccess(
                            scrapList = uiState.loadState.data.toImmutableList(),
                            onScrapButtonClicked = onClickScrapButton,
                            onInternshipClicked = onClickInternship
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarWeekEmpty(
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
        modifier = modifier
            .fillMaxWidth(),
        text = stringResource(id = R.string.calendar_empty_scrap),
        textAlign = TextAlign.Center,
        style = TerningTheme.typography.body5,
        color = Grey400
    )
}

@Composable
private fun CalendarWeekSuccess(
    scrapList: List<CalendarScrapDetail>,
    onScrapButtonClicked: (Long) -> Unit,
    onInternshipClicked: (CalendarScrapDetail) -> Unit,
) {
    CalendarScrapListGroup(
        scrapList = scrapList,
        onScrapButtonClicked = onScrapButtonClicked,
        onInternshipClicked = onInternshipClicked,
        modifier = Modifier
            .padding(horizontal = 24.dp)
    )
}




