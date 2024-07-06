package com.terning.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate


class CalendarViewModel: ViewModel() {
    private val _selectedDate = MutableStateFlow<LocalDate>(LocalDate.now())
    val selectedDate get() = _selectedDate.asStateFlow()

    fun updateSelectedDate(date: LocalDate) = viewModelScope.launch{
            if(_selectedDate.value != date) {
                _selectedDate.value = date
            }
        }


    fun initDateToToday() = viewModelScope.launch {
            _selectedDate.value = LocalDate.now()
        }

}