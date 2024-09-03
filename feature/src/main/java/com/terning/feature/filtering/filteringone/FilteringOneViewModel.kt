package com.terning.feature.filtering.filteringone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilteringOneViewModel : ViewModel() {

    private val _state: MutableStateFlow<FilteringOneState> = MutableStateFlow(FilteringOneState())
    val state: StateFlow<FilteringOneState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<FilteringOneSideEffect>()
    val sideEffects: SharedFlow<FilteringOneSideEffect> get() = _sideEffects.asSharedFlow()

    fun updateButtonValidation() {
        _state.value = _state.value.copy(isButtonValid = true)
    }

    fun updateGrade(grade: Int) {
        _state.value = _state.value.copy(grade = grade)
    }

    fun navigateUp() =
        viewModelScope.launch { _sideEffects.emit(FilteringOneSideEffect.NavigateUp) }
}