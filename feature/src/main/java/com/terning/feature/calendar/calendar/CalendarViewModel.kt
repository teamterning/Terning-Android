package com.terning.feature.calendar.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.state.UiState
import com.terning.domain.repository.CalendarRepository
import com.terning.feature.R
import com.terning.feature.calendar.month.ScrapCalendarState
import com.terning.feature.calendar.scrap.model.Scrap
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

    private var _selectedDate:MutableStateFlow<CalendarUiState> = MutableStateFlow(
        CalendarUiState(
            selectedDate = LocalDate.now(),
            isListEnabled = false
        )
    )

    val selectedDate get() = _selectedDate.asStateFlow()

    private val _scrapCalendarState = MutableStateFlow(ScrapCalendarState())
    val scrapCalendarState = _scrapCalendarState.asStateFlow()

    private val _calendarSideEffect:MutableSharedFlow<CalendarSideEffect> = MutableSharedFlow()
    val calendarSideEffect = _calendarSideEffect.asSharedFlow()

    fun updateSelectedDate(date: LocalDate) = viewModelScope.launch {
        if (_selectedDate.value.selectedDate != date) {
            _selectedDate.update { currentState ->
                currentState.copy(
                    selectedDate = date,
                    isWeekEnabled = true
                )
            }
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
                _scrapCalendarState.update { currentState ->
                    currentState.copy(
                        loadState = UiState.Success(it)
                    )
                }
                _calendarSideEffect.emit(CalendarSideEffect.ShowToast(R.string.server_success))
            },
            onFailure = {
                _calendarSideEffect.emit(CalendarSideEffect.ShowToast(R.string.server_failure))
            }
        )
    }


    //To be erased in future
    val mockScrapList: List<List<Scrap>>
        get() {
            val list: MutableList<List<Scrap>> = mutableListOf()
            for (i in 0..30) {
                when (i % 6) {
                    0 -> {
                        list.add(
                            i,
                            listOf()
                        )
                    }

                    1 -> {
                        list.add(
                            i,
                            listOf(
                                Scrap(
                                    "Task1_1",
                                    CalBlue1,
                                    dDay = "1",
                                    period = "3",
                                    isScraped = true
                                ),
                            )
                        )
                    }

                    2 -> {
                        list.add(
                            i,
                            listOf(
                                Scrap(
                                    "Task2_1",
                                    CalPink,
                                    dDay = "2",
                                    period = "3",
                                    isScraped = true
                                ),
                                Scrap(
                                    "Task2_2",
                                    CalGreen1,
                                    dDay = "2",
                                    period = "3",
                                    isScraped = true
                                )
                            )
                        )
                    }

                    3 -> {
                        list.add(
                            i,
                            listOf()
                        )
                    }

                    4 -> {
                        list.add(
                            i,
                            listOf()
                        )
                    }

                    5 -> {
                        list.add(
                            i,
                            listOf(
                                Scrap(
                                    "Task3_1",
                                    CalPink,
                                    dDay = "5",
                                    period = "3",
                                    isScraped = true
                                ),
                                Scrap(
                                    "Task3_2",
                                    CalPurple,
                                    dDay = "5",
                                    period = "3",
                                    isScraped = true
                                ),
                                Scrap(
                                    "Task3_3",
                                    CalRed,
                                    dDay = "5",
                                    period = "3",
                                    isScraped = true
                                ),
                                Scrap(
                                    "Task3_4",
                                    CalBlue1,
                                    dDay = "5",
                                    period = "3",
                                    isScraped = true
                                ),
                                Scrap(
                                    "Task3_5",
                                    CalGreen2,
                                    dDay = "5",
                                    period = "3",
                                    isScraped = true
                                ),
                                Scrap(
                                    "Task3_6",
                                    CalYellow,
                                    dDay = "5",
                                    period = "3",
                                    isScraped = true
                                )
                            )
                        )
                    }
                }
            }
            return list.toList()
        }
}