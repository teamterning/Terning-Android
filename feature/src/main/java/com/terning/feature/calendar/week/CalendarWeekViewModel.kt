package com.terning.feature.calendar.week

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange1
import com.terning.core.designsystem.theme.CalOrange2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.state.UiState
import com.terning.domain.entity.calendar.CalendarScrapDetail
import com.terning.domain.entity.calendar.CalendarScrapRequest
import com.terning.domain.repository.CalendarRepository
import com.terning.domain.repository.ScrapRepository
import com.terning.feature.R
import com.terning.feature.calendar.week.model.CalendarWeekUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarWeekViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository,
    private val scrapRepository: ScrapRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(CalendarWeekUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<CalendarWeekSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun updateSelectedDate(selectedDate: LocalDate) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedDate = selectedDate
            )
        }
    }

    fun updateScrapCancelDialogVisibility(visibility: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                scrapDialogVisibility = visibility
            )
        }
    }

    fun updateScrapId(scrapId: Long? = null) {
        _uiState.update { currentState ->
            currentState.copy(
                internshipAnnouncementId = scrapId
            )
        }
    }

    fun updateInternDialogVisibility(visibility: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                internDialogVisibility = visibility
            )
        }
    }

    fun updateInternshipModel(scrapDetailModel: CalendarScrapDetail?) {
        _uiState.update { currentState ->
            currentState.copy(
                internshipModel = scrapDetailModel
            )
        }
    }

    fun getScrapWeekList(selectedDate: LocalDate) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            calendarRepository.getScrapDayList(selectedDate)
        }.fold(
            onSuccess = {
                _uiState.update { currentState ->
                    currentState.copy(
                        loadState = if (it.isNotEmpty()) UiState.Success(it) else UiState.Empty
                    )
                }
            },
            onFailure = {
                _uiState.update { currentState ->
                    currentState.copy(
                        loadState = UiState.Failure(it.message.toString())
                    )

                }
                _sideEffect.emit(CalendarWeekSideEffect.ShowToast(R.string.server_failure))
            }
        )
    }

    fun deleteScrap(scrapId: Long) = viewModelScope.launch {
        _uiState.value.loadState
            .takeIf { it is UiState.Success }
            ?.let { CalendarScrapRequest(scrapId, null) }?.let { scrapRequestModel ->
                scrapRepository.deleteScrap(
                    scrapRequestModel
                ).onSuccess {
                    runCatching {
                            getScrapWeekList(selectedDate = _uiState.value.selectedDate)
                    }.onSuccess {
                        updateScrapCancelDialogVisibility(false)
                    }
                }.onFailure {
                    _sideEffect.emit(
                        CalendarWeekSideEffect.ShowToast(R.string.server_failure)
                    )
                }
            }
    }

    fun patchScrap(color: Color) = viewModelScope.launch {
        val scrapId = _uiState.value.internshipModel?.internshipAnnouncementId ?: 0
        val colorIndex = getColorIndex(color)

        scrapRepository.patchScrap(CalendarScrapRequest(scrapId, colorIndex))
            .onSuccess {
                runCatching {
                    getScrapWeekList(selectedDate = _uiState.value.selectedDate)
                }
            }.onFailure {
                _sideEffect.emit(CalendarWeekSideEffect.ShowToast(R.string.server_failure))
            }
    }

    private fun getColorIndex(color: Color): Int = listOf(
        CalRed,
        CalOrange1,
        CalOrange2,
        CalYellow,
        CalGreen1,
        CalGreen2,
        CalBlue1,
        CalBlue2,
        CalPurple,
        CalPink
    ).indexOf(color)
}