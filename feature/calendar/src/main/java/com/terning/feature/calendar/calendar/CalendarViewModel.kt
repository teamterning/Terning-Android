package com.terning.feature.calendar.calendar

import androidx.lifecycle.ViewModel
import com.terning.feature.calendar.calendar.model.DayModel
import com.terning.feature.calendar.calendar.model.CalendarUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {
    private var _uiState: MutableStateFlow<CalendarUiState> = MutableStateFlow(CalendarUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onSelectNewDate(selectedDate: DayModel)  {
        if (_uiState.value.selectedDate.date == selectedDate.date) {
            updateWeekVisibility(!_uiState.value.isWeekEnabled)
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedDate = selectedDate,
                    isWeekEnabled = true
                )
            }
        }
    }

    fun updateSelectedDate(value: DayModel) = _uiState.update { currentState ->
            currentState.copy(
                selectedDate = value
            )
        }


    fun updateListVisibility(value: Boolean) =  _uiState.update { currentState ->
            currentState.copy(
                isListEnabled = value
            )
        }


    fun updateWeekVisibility(value: Boolean) =  _uiState.update { currentState ->
            currentState.copy(
                isWeekEnabled = value
            )
        }
}