package com.terning.feature.calendar.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.feature.calendar.calendar.model.CalendarUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {
    private var _uiState: MutableStateFlow<CalendarUiState> = MutableStateFlow(CalendarUiState())
    val uiState get() = _uiState.asStateFlow()

    fun updateSelectedDate(date: LocalDate) = viewModelScope.launch {
        if (_uiState.value.selectedDate != date) {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedDate = date
                )
            }
            updateWeekVisibility(true)
        } else {
            updateWeekVisibility(!_uiState.value.isWeekEnabled)
        }
    }

    fun updateListVisibility(
        visibility: Boolean
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                isListEnabled = visibility
            )
        }
    }

    fun updateWeekVisibility(
        visibility: Boolean
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                isWeekEnabled = visibility
            )
        }
    }
}