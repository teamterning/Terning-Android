package com.terning.feature.calendar.week

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.CalendarScrapDetail
import com.terning.domain.repository.CalendarRepository
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
    private val calendarRepository: CalendarRepository
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
}