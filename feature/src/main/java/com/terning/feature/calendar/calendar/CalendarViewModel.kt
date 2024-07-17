package com.terning.feature.calendar.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.repository.CalendarRepository
import com.terning.feature.R
import com.terning.feature.calendar.month.CalendarMonthState
import com.terning.feature.calendar.scrap.CalendarListState
import com.terning.feature.calendar.week.CalendarWeekState
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
class CalendarViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
) : ViewModel() {

    private var _selectedDate: MutableStateFlow<CalendarUiState> = MutableStateFlow(
        CalendarUiState(
            selectedDate = LocalDate.now(),
            isListEnabled = false
        )
    )

    val selectedDate get() = _selectedDate.asStateFlow()

    private val _calendarMonthState = MutableStateFlow(CalendarMonthState())
    val calendarMonthState = _calendarMonthState.asStateFlow()

    private val _calendarListState = MutableStateFlow(CalendarListState())
    val calendarListState = _calendarListState.asStateFlow()

    private val _calendarWeekState = MutableStateFlow(CalendarWeekState())
    val calendarWeekState = _calendarWeekState.asStateFlow()

    private val _calendarSideEffect: MutableSharedFlow<CalendarSideEffect> = MutableSharedFlow()
    val calendarSideEffect = _calendarSideEffect.asSharedFlow()

    fun updateSelectedDate(date: LocalDate) = viewModelScope.launch {
        if (_selectedDate.value.selectedDate != date) {
            _selectedDate.update { currentState ->
                currentState.copy(
                    selectedDate = date,
                    isWeekEnabled = true
                )
            }
            getScrapWeekList()
        } else {
            _selectedDate.update { currentState ->
                currentState.copy(
                    isWeekEnabled = !currentState.isWeekEnabled
                )
            }
        }
    }

    fun changeListVisibility() {
        _selectedDate.update { currentState ->
            currentState.copy(
                isListEnabled = !currentState.isListEnabled
            )
        }
    }

    fun disableWeekCalendar() {
        _selectedDate.update { currentState ->
            currentState.copy(
                isWeekEnabled = false
            )
        }
    }

    fun getScrapMonth(
        year: Int, month: Int
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            calendarRepository.getScrapMonth(year, month)
        }.fold(
            onSuccess = {
                _calendarMonthState.update { currentState ->
                    currentState.copy(
                        loadState = UiState.Success(it)
                    )
                }
            },
            onFailure = {
                _calendarSideEffect.emit(CalendarSideEffect.ShowToast(R.string.server_failure))
            }
        )
    }

    fun getScrapMonthList(
        year: Int, month: Int
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            calendarRepository.getScrapMonthList(year, month)
        }.fold(
            onSuccess = {
                _calendarListState.update { currentState ->
                    currentState.copy(
                        loadState = if (it.isNotEmpty()) UiState.Success(it) else UiState.Empty
                        //loadState = UiState.Success(it)
                    )
                }
            },
            onFailure = {
                _calendarListState.update { currentState ->
                    currentState.copy(
                        loadState = UiState.Failure(it.message.toString())
                    )

                }
                _calendarSideEffect.emit(CalendarSideEffect.ShowToast(R.string.server_failure))
            }
        )
    }

    fun getScrapWeekList() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            calendarRepository.getScrapDayList(_selectedDate.value.selectedDate)
        }.fold(
            onSuccess = {
                _calendarWeekState.update { currentState ->
                    currentState.copy(
                        loadState = if (it.isNotEmpty()) UiState.Success(it) else UiState.Empty
                        //loadState = UiState.Success(it)
                    )
                }
            },
            onFailure = {
                _calendarWeekState.update { currentState ->
                    currentState.copy(
                        loadState = UiState.Failure(it.message.toString())
                    )

                }
                _calendarSideEffect.emit(CalendarSideEffect.ShowToast(R.string.server_failure))
            }
        )
    }
}