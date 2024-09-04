package com.terning.feature.calendar.list

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
import com.terning.feature.calendar.list.model.CalendarListUiState
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
class CalendarListViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository,
    private val scrapRepository: ScrapRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(CalendarListUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<CalendarListSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun updateCurrentDate(date: LocalDate) {
        _uiState.update { currentState ->
            currentState.copy(
                currentDate = date
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
                scrapId = scrapId
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

    fun getScrapMonthList(
        date: LocalDate
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            calendarRepository.getScrapMonthList(
                year = _uiState.value.currentDate.year,
                month = _uiState.value.currentDate.monthValue
            )
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
                _sideEffect.emit(CalendarListSideEffect.ShowToast(R.string.server_failure))
            }
        )
    }

    fun deleteScrap(
        scrapId: Long
    ) = viewModelScope.launch {
        _uiState.value.loadState
            .takeIf { it is UiState.Success }
            ?.let { CalendarScrapRequest(scrapId, null) }?.let { scrapRequestModel ->
                scrapRepository.deleteScrap(
                    scrapRequestModel
                ).onSuccess {
                    runCatching {
                        getScrapMonthList(_uiState.value.currentDate)
                    }.onSuccess {
                        updateScrapCancelDialogVisibility(false)
                    }
                }.onFailure {
                    _sideEffect.emit(CalendarListSideEffect.ShowToast(R.string.server_failure))
                }
            }
    }

    fun patchScrap(
        color: Color
    ) = viewModelScope.launch {
        val scrapId = _uiState.value.internshipModel?.scrapId ?: 0
        val colorIndex = getColorIndex(color)

        scrapRepository.patchScrap(CalendarScrapRequest(scrapId, colorIndex))
            .onSuccess {
                runCatching {
                    getScrapMonthList(_uiState.value.currentDate)
                }
            }.onFailure {
                _sideEffect.emit(CalendarListSideEffect.ShowToast(R.string.server_failure))
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