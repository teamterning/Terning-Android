package com.terning.feature.filtering.filteringtwo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilteringTwoViewModel : ViewModel() {

    private val _state: MutableStateFlow<FilteringTwoState> = MutableStateFlow(FilteringTwoState())
    val state: StateFlow<FilteringTwoState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<FilteringTwoSideEffect>()
    val sideEffects: SharedFlow<FilteringTwoSideEffect> get() = _sideEffects.asSharedFlow()

    fun updateButton(isButtonValid: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                isButtonValid = isButtonValid
            )
        }
    }

    fun updateWorkingPeriod(workingPeriod: String) {
        _state.value = _state.value.copy(
            workingPeriod = workingPeriod,
        )
    }

    fun navigateUp() =
        viewModelScope.launch { _sideEffects.emit(FilteringTwoSideEffect.NavigateUp) }

    fun navigateToFilteringThree(grade: String, workingPeriod: String) =
        viewModelScope.launch {
            _sideEffects.emit(
                FilteringTwoSideEffect.NavigateToFilteringThree(
                    grade, workingPeriod
                )
            )
        }

}