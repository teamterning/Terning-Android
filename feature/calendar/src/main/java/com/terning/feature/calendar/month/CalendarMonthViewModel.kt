package com.terning.feature.calendar.month

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.state.UiState
import com.terning.domain.calendar.repository.CalendarRepository
import com.terning.feature.calendar.month.model.CalendarMonthUiState
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
import com.terning.core.designsystem.R as DesignSystemR

@HiltViewModel
class CalendarMonthViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(CalendarMonthUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<CalendarMonthSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun getScrapMonth(
        date: LocalDate
    ) = viewModelScope.launch(Dispatchers.IO) {
        calendarRepository.getScrapMonth(date.year, date.monthValue)
            .fold(
                onSuccess = {
                    _uiState.update { currentState ->
                        currentState.copy(
                            loadState = UiState.Success(it)
                        )
                    }
                },
                onFailure = {
                    _sideEffect.emit(CalendarMonthSideEffect.ShowToast(DesignSystemR.string.server_failure))
                }
            )
    }
}