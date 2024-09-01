package com.terning.feature.calendar.week

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.getFullDateStringInKorean
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.CalendarScrapDetail
import com.terning.feature.R
import com.terning.feature.calendar.calendar.model.CalendarUiState
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
    modifier: Modifier = Modifier,
    viewModel: CalendarWeekViewModel = hiltViewModel()
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

    LaunchedEffect(key1 = calendarUiState.selectedDate) {
        viewModel.updateSelectedDate(selectedDate = calendarUiState.selectedDate)
    }

    LaunchedEffect(key1 = uiState.selectedDate) {
        viewModel.getScrapWeekList(selectedDate = uiState.selectedDate)
    }

    CalendarWeekScreen(
        modifier = modifier,
        uiState = uiState,
        selectedDate = calendarUiState.selectedDate,
        updateSelectedDate = updateSelectedDate,
        navigateToAnnouncement = { announcementId ->
            navigateToAnnouncement(announcementId)
            viewModel.updateInternDialogVisibility(false)
        },
        onDismissCancelDialog = { isCancelled ->
            viewModel.updateScrapCancelDialogVisibility(false)
            if (isCancelled) {
                viewModel.getScrapWeekList(uiState.selectedDate)
            }
        },
        onDismissInternDialog = { viewModel.updateInternDialogVisibility(false) },
        onClickChangeColor = { viewModel.getScrapWeekList(uiState.selectedDate) },
        onClickScrapCancel = { uiState.scrapId?.let { viewModel.deleteScrap(it) } },
        onClickScrapButton = { scrapId ->
            with(viewModel) {
                updateScrapId(scrapId)
                updateScrapCancelDialogVisibility(true)
            }
        },
        onClickInternship = { scrapDetail ->
            with(viewModel) {
                updateInternDialogVisibility(true)
                updateInternshipModel(scrapDetail)
            }
        },
    )
}

@Composable
private fun CalendarWeekScreen(
    uiState: CalendarWeekUiState,
    selectedDate: LocalDate,
    updateSelectedDate: (LocalDate) -> Unit,
    onDismissCancelDialog: (Boolean) -> Unit,
    onDismissInternDialog: () -> Unit,
    onClickChangeColor: () -> Unit,
    onClickScrapCancel: () -> Unit,
    onClickInternship: (CalendarScrapDetail) -> Unit,
    onClickScrapButton: (Long) -> Unit,
    navigateToAnnouncement: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var initialTouchPosition by remember { mutableStateOf<Offset?>(null) }
    var hideComponent by remember { mutableStateOf(false) }

    LaunchedEffect(hideComponent) {
        if (hideComponent) {
            updateSelectedDate(selectedDate)
        }
    }

    val swipeModifier = Modifier.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()
                val position = event.changes.first().position

                if (event.changes.first().pressed) {
                    if (initialTouchPosition == null) {
                        initialTouchPosition = position
                    } else {
                        val deltaY = initialTouchPosition?.let { position.y - it.y }
                        if (deltaY != null && deltaY > 300f) {
                            hideComponent = true
                        }
                    }
                } else {
                    initialTouchPosition = null
                }
            }
        }
    }

    Column(
        modifier = modifier
            .background(Back)
    ) {
        Card(
            modifier = swipeModifier
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

        when (uiState.loadState) {
            is UiState.Loading -> {}
            is UiState.Empty -> {
                CalendarWeekEmpty()
            }

            is UiState.Failure -> {}
            is UiState.Success -> {
                CalendarWeekSuccess(
                    scrapList = uiState.loadState.data.toImmutableList(),
                    selectedDate = uiState.selectedDate,
                    onScrapButtonClicked = onClickScrapButton,
                    onInternshipClicked = onClickInternship
                )
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
                deadline = uiState.selectedDate.getFullDateStringInKorean(),
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

@Composable
private fun CalendarWeekEmpty(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
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

@Composable
private fun CalendarWeekSuccess(
    scrapList: List<CalendarScrapDetail>,
    onScrapButtonClicked: (Long) -> Unit,
    onInternshipClicked: (CalendarScrapDetail) -> Unit,
    selectedDate: LocalDate
) {
    CalendarScrapList(
        selectedDate = selectedDate,
        scrapList = scrapList,
        onScrapButtonClicked = onScrapButtonClicked,
        onInternshipClicked = onInternshipClicked
    )
}




