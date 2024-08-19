package com.terning.feature.calendar.calendar

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
import com.terning.domain.entity.request.ScrapRequestModel
import com.terning.domain.entity.response.CalendarScrapDetailModel
import com.terning.domain.repository.CalendarRepository
import com.terning.domain.repository.ScrapRepository
import com.terning.feature.R
import com.terning.feature.calendar.calendar.model.CalendarUiState
import com.terning.feature.calendar.month.model.CalendarMonthState
import com.terning.feature.calendar.list.model.CalendarListState
import com.terning.feature.calendar.week.model.CalendarWeekState
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
    private val calendarRepository: CalendarRepository,
    private val scrapRepository: ScrapRepository
) : ViewModel() {

    private var _uiState: MutableStateFlow<CalendarUiState> = MutableStateFlow(
        CalendarUiState(
            selectedDate = LocalDate.now(),
            isListEnabled = false
        )
    )

    val uiState get() = _uiState.asStateFlow()

    private val _calendarMonthState = MutableStateFlow(CalendarMonthState())
    val calendarMonthState = _calendarMonthState.asStateFlow()

    private val _calendarListState = MutableStateFlow(CalendarListState())
    val calendarListState = _calendarListState.asStateFlow()

    private val _calendarWeekState = MutableStateFlow(CalendarWeekState())
    val calendarWeekState = _calendarWeekState.asStateFlow()

    private val _calendarSideEffect: MutableSharedFlow<CalendarSideEffect> = MutableSharedFlow()
    val calendarSideEffect = _calendarSideEffect.asSharedFlow()

    fun updateSelectedDate(date: LocalDate) = viewModelScope.launch {
        if (_uiState.value.selectedDate != date) {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedDate = date,
                    isWeekEnabled = true
                )
            }
            getScrapWeekList()
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isWeekEnabled = !currentState.isWeekEnabled
                )
            }
        }
    }

    fun changeListVisibility() {
        _uiState.update { currentState ->
            currentState.copy(
                isListEnabled = !currentState.isListEnabled
            )
        }
    }

    fun disableWeekCalendar() {
        _uiState.update { currentState ->
            currentState.copy(
                isWeekEnabled = false
            )
        }
    }

    fun updateScrapCancelDialogVisible(scrapId: Long = -1) {
        _uiState.update { currentState ->
            currentState.copy(
                isScrapButtonClicked = !currentState.isScrapButtonClicked,
                scrapId = scrapId
            )
        }
    }

    fun updateInternDialogVisible(visibility: Boolean = false) {
        _uiState.update { currentState ->
            currentState.copy(
                isInternshipClicked = visibility
            )
        }
    }

    fun updateInternshipModel(scrapDetailModel: CalendarScrapDetailModel?) {
        _uiState.update { currentState ->
            currentState.copy(
                internshipModel = scrapDetailModel
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
            calendarRepository.getScrapDayList(_uiState.value.selectedDate)
        }.fold(
            onSuccess = {
                _calendarWeekState.update { currentState ->
                    currentState.copy(
                        loadState = if (it.isNotEmpty()) UiState.Success(it) else UiState.Empty
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

    fun deleteScrap(isFromWeekScreen: Boolean = false) = viewModelScope.launch {
        _calendarWeekState.value.loadState
            .takeIf { it is UiState.Success }
            ?.let { ScrapRequestModel(_uiState.value.scrapId, null) }?.let { scrapRequestModel ->
                scrapRepository.deleteScrap(
                    scrapRequestModel
                ).onSuccess {
                    runCatching {
                        if (isFromWeekScreen) {
                            getScrapWeekList()
                        } else {
                            getScrapMonthList(
                                _uiState.value.selectedDate.year,
                                _uiState.value.selectedDate.monthValue
                            )
                        }
                    }.onSuccess {
                        updateScrapCancelDialogVisible()
                    }
                }.onFailure {
                    _calendarSideEffect.emit(
                        CalendarSideEffect.ShowToast(R.string.server_failure)
                    )
                }
            }
    }

    fun patchScrap(color: Color, isFromWeekScreen: Boolean = false) = viewModelScope.launch {
        val scrapId = _uiState.value.internshipModel?.scrapId ?: 0
        val colorIndex = getColorIndex(color)

        scrapRepository.patchScrap(ScrapRequestModel(scrapId, colorIndex))
            .onSuccess {
                runCatching {
                    if (isFromWeekScreen) {
                        getScrapWeekList()
                    } else {
                        getScrapMonthList(
                            _uiState.value.selectedDate.year,
                            _uiState.value.selectedDate.monthValue
                        )
                    }
                }
            }.onFailure {
                _calendarSideEffect.emit(CalendarSideEffect.ShowToast(R.string.server_failure))
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