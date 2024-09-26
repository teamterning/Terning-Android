package com.terning.feature.calendar.week

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.getDateStringInKorean
import com.terning.core.extension.getFullDateStringInKorean
import com.terning.core.extension.swipableVertically
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.calendar.CalendarScrapDetail
import com.terning.feature.R
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.calendar.calendar.model.LocalPagerState
import com.terning.feature.calendar.list.component.CalendarScrapList
import com.terning.feature.calendar.week.component.HorizontalCalendarWeek
import com.terning.feature.calendar.week.model.CalendarWeekUiState
import com.terning.feature.dialog.cancel.ScrapCancelDialog
import com.terning.feature.dialog.detail.ScrapDialog
import okhttp3.internal.toImmutableList
import java.time.LocalDate

@Composable
fun CalendarWeekRoute(
    calendarUiState: CalendarUiState,
    navigateToAnnouncement: (Long) -> Unit,
    updateSelectedDate: (LocalDate) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalendarWeekViewModel = hiltViewModel(),
) {
    val pagerState = LocalPagerState.current
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

    LaunchedEffect(key1 = calendarUiState.selectedDate) {
        viewModel.getScrapWeekList(selectedDate = calendarUiState.selectedDate)
    }

    BackHandler {
        navigateUp()
    }

    CalendarWeekScreen(
        modifier = modifier,
        pagerState = pagerState,
        uiState = uiState,
        selectedDate = calendarUiState.selectedDate,
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

    CalendarWeekScrapPatchDialog(
        currentDate = calendarUiState.selectedDate,
        dialogVisibility = uiState.scrapDetailDialogVisibility,
        internshipModel = uiState.internshipModel,
        navigateToAnnouncement = { announcementId ->
            navigateToAnnouncement(announcementId)
            viewModel.updateScrapDetailDialogVisibility(false)
        },
        onDismissInternDialog = { viewModel.updateScrapDetailDialogVisibility(false) },
        onClickChangeColor = {
            viewModel.getScrapWeekList(calendarUiState.selectedDate)
        },
    )

    CalendarWeekScrapCancelDialog(
        scrapVisibility = uiState.scrapCancelDialogVisibility,
        internshipAnnouncementId = uiState.internshipAnnouncementId,
        onDismissCancelDialog = { isCancelled ->
            viewModel.updateScrapCancelDialogVisibility(false)
            if (isCancelled) {
                viewModel.getScrapWeekList(calendarUiState.selectedDate)
            }
        }
    )
}

@Composable
private fun CalendarWeekScreen(
    uiState: CalendarWeekUiState,
    pagerState: PagerState,
    selectedDate: LocalDate,
    updateSelectedDate: (LocalDate) -> Unit,
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

            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
        ) {
            HorizontalCalendarWeek(
                selectedDate = selectedDate,
                onDateSelected = updateSelectedDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
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
                    text = selectedDate.getDateStringInKorean(),
                    style = TerningTheme.typography.title5,
                    color = Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 16.dp, bottom = 15.dp)
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
                            selectedDate = selectedDate,
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
    selectedDate: LocalDate,
) {
    CalendarScrapList(
        scrapList = scrapList,
        onScrapButtonClicked = onScrapButtonClicked,
        onInternshipClicked = onInternshipClicked,
        modifier = Modifier
            .padding(horizontal = 24.dp)
    )
}

@Composable
private fun CalendarWeekScrapCancelDialog(
    scrapVisibility: Boolean,
    internshipAnnouncementId: Long?,
    onDismissCancelDialog: (Boolean) -> Unit,
) {
    if (scrapVisibility) {
        internshipAnnouncementId?.run {
            ScrapCancelDialog(
                internshipAnnouncementId = this,
                onDismissRequest = onDismissCancelDialog
            )
        }
    }
}

@Composable
private fun CalendarWeekScrapPatchDialog(
    currentDate: LocalDate,
    dialogVisibility: Boolean,
    internshipModel: CalendarScrapDetail?,
    navigateToAnnouncement: (Long) -> Unit,
    onDismissInternDialog: (Boolean) -> Unit,
    onClickChangeColor: () -> Unit,
) {
    if (dialogVisibility) {
        internshipModel?.let { internship ->
            val scrapColor = Color(
                android.graphics.Color.parseColor(
                    internship.color
                )
            )
            ScrapDialog(
                title = internship.title,
                scrapColor = scrapColor,
                deadline = currentDate.getFullDateStringInKorean(),
                startYearMonth = internship.startYearMonth,
                workingPeriod = internship.workingPeriod,
                internshipAnnouncementId = internship.internshipAnnouncementId,
                companyImage = internship.companyImage,
                isScrapped = true,
                onDismissRequest = onDismissInternDialog,
                onClickChangeColor = onClickChangeColor,
                onClickNavigateButton = navigateToAnnouncement
            )
        }
    }
}




