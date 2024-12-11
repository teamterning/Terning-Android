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
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.extension.getFullDateStringInKorean
import com.terning.core.designsystem.extension.isListNotEmpty
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.domain.calendar.entity.CalendarScrapDetail
import com.terning.feature.calendar.R
import com.terning.feature.calendar.calendar.model.TerningCalendarModel.Companion.LocalCalendarModel
import com.terning.feature.calendar.calendar.model.LocalPagerState
import com.terning.feature.calendar.calendar.component.group.CalendarScrapListGroup
import com.terning.feature.calendar.list.model.CalendarListUiState
import com.terning.feature.dialog.cancel.ScrapCancelDialog
import com.terning.feature.dialog.detail.ScrapDialog
import okhttp3.internal.toImmutableList
import java.time.LocalDate

@Composable
fun CalendarListRoute(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    navigateToAnnouncement: (Long) -> Unit,
    viewModel: CalendarListViewModel = hiltViewModel(),
) {
    val pagerState = LocalPagerState.current
    val calendarModel = LocalCalendarModel.current
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

    CalendarListScrapPatchDialog(
        currentDate = uiState.currentDate,
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

    CalendarListScrapCancelDialog(
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
    uiState: CalendarListUiState,
    onClickInternship: (CalendarScrapDetail) -> Unit,
    onClickScrapButton: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val calendarModel = LocalCalendarModel.current

    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { page ->
        val getDate = calendarModel.getLocalDateByPage(page)

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
                    val scrapMap = uiState.loadState.data
                    items(getDate.lengthOfMonth()) { day ->
                        val currentDate =
                            LocalDate.of(getDate.year, getDate.monthValue, day + 1)
                        val dateInKorean = currentDate.getFullDateStringInKorean()

                        if (scrapMap[dateInKorean].isListNotEmpty()) {
                            Text(
                                text = dateInKorean,
                                style = TerningTheme.typography.title5,
                                color = Black,
                                modifier = Modifier
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
private fun CalendarListScrapCancelDialog(
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
private fun CalendarListScrapPatchDialog(
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
