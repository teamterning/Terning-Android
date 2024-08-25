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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.terning.core.extension.getFullDateStringInKorean
import com.terning.core.state.UiState
import com.terning.domain.entity.CalendarScrapDetail
import com.terning.feature.R
import com.terning.feature.calendar.calendar.component.CalendarCancelDialog
import com.terning.feature.calendar.calendar.component.CalendarDetailDialog
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.calendar.list.component.CalendarScrapList
import com.terning.feature.calendar.week.component.HorizontalCalendarWeek
import com.terning.feature.calendar.week.model.CalendarWeekUiState
import okhttp3.internal.toImmutableList
import java.time.LocalDate

@Composable
fun CalendarWeekRoute(
    modifier: Modifier = Modifier,
    calendarUiState: CalendarUiState,
    navigateToAnnouncement: (Long) -> Unit,
    updateSelectedDate: (LocalDate) -> Unit,
    viewModel: CalendarWeekViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(lifecycleOwner)

    LaunchedEffect(key1 = calendarUiState.selectedDate) {
        viewModel.updateSelectedDate(selectedDate = calendarUiState.selectedDate)
    }

    LaunchedEffect(key1 = uiState.selectedDate) {
        viewModel.getScrapWeekList(selectedDate = uiState.selectedDate)
    }

    if (uiState.scrapDialogVisibility) {
        CalendarCancelDialog(
            onDismissRequest = {
                viewModel.updateScrapCancelDialogVisibility(false)
            },
            onClickScrapCancel = {
                viewModel.updateScrapCancelDialogVisibility(false)
                uiState.scrapId?.let { viewModel.deleteScrap(it) }
            }
        )
    }

    if (uiState.internDialogVisibility) {
        CalendarDetailDialog(
            deadline = uiState.selectedDate.getFullDateStringInKorean(),
            scrapDetailModel = uiState.internshipModel,
            onDismissRequest = {
                viewModel.updateInternDialogVisibility(false)
            },
            onClickChangeColorButton = { newColor ->
                viewModel.patchScrap(newColor)
            },
            onClickNavigateButton = { announcementId ->
                navigateToAnnouncement(announcementId)
                viewModel.updateInternDialogVisibility(false)
            }
        )
    }

    CalendarWeekScreen(
        modifier = modifier,
        uiState = uiState,
        selectedDate = calendarUiState.selectedDate,
        updateSelectedDate = updateSelectedDate,
        onScrapButtonClicked = { scrapId->
            with(viewModel) {
                updateScrapCancelDialogVisibility(true)
                updateScrapId(scrapId)
            }
        },
        onInternshipClicked = { scrapDetail ->
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
    onScrapButtonClicked: (Long) -> Unit,
    onInternshipClicked: (CalendarScrapDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Back)
    ) {
        Card(
            modifier = Modifier
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
                    onScrapButtonClicked = onScrapButtonClicked,
                    onInternshipClicked = onInternshipClicked
                )
            }
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




