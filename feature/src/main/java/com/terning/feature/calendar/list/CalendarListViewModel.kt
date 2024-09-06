package com.terning.feature.calendar.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.calendar.CalendarScrapDetail
import com.terning.domain.entity.calendar.CalendarScrapRequest
import com.terning.domain.repository.CalendarRepository
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
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarListViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
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

    fun updateAnnouncementId(announcementId: Long? = null) {
        _uiState.update { currentState ->
            currentState.copy(
                internshipAnnouncementId = announcementId
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
    ) = viewModelScope.launch(Dispatchers.IO) {
            calendarRepository.getScrapMonthList(
                year = _uiState.value.currentDate.year,
                month = _uiState.value.currentDate.monthValue
            )
        .fold(
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
}
