package com.terning.feature.filtering.filteringtwo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilteringTwoViewModel : ViewModel() {

    private val _state: MutableStateFlow<FilteringTwoState> = MutableStateFlow(FilteringTwoState())
    val state: StateFlow<FilteringTwoState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<FilteringTwoSideEffect>()
    val sideEffects: SharedFlow<FilteringTwoSideEffect> get() = _sideEffects.asSharedFlow()

    fun updateWorkingPeriodAndButton(workingPeriod: Int) {
        _state.value = _state.value.copy(
            workingPeriod = workingPeriod,
            isButtonValid = true
        )
    }

    fun navigateUp() =
        viewModelScope.launch { _sideEffects.emit(FilteringTwoSideEffect.NavigateUp) }
}