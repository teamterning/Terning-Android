package com.terning.feature.filtering.filteringthree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.domain.entity.filtering.Filtering
import com.terning.domain.repository.FilteringRepository
import com.terning.domain.repository.TokenRepository
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilteringViewModel @Inject constructor(
    private val filteringRepository: FilteringRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FilteringState())
    val state: StateFlow<FilteringState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<FilteringThreeSideEffect>()
    val sideEffects: SharedFlow<FilteringThreeSideEffect> get() = _sideEffects.asSharedFlow()

    fun updateGrade(grade: Int) {
        _state.value = _state.value.copy(grade = grade)
    }

    fun updateWorkingPeriod(workingPeriod: Int) {
        _state.value = _state.value.copy(workingPeriod = workingPeriod)
    }

    fun updateStartYear(startYear: Int) {
        _state.value = _state.value.copy(startYear = startYear)
    }

    fun updateStartMonth(startMonth: Int) {
        _state.value = _state.value.copy(startMonth = startMonth)
    }

    fun postFilteringWithServer() {
        viewModelScope.launch {
            filteringRepository.postFiltering(
                tokenRepository.getUserId(),
                state.value.run {
                    Filtering(
                        grade = grade,
                        workingPeriod = workingPeriod,
                        startYear = startYear,
                        startMonth = startMonth
                    )
                }
            ).onSuccess {
                _sideEffects.emit(FilteringThreeSideEffect.NavigateToStartHome)
            }.onFailure {
                _sideEffects.emit(FilteringThreeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun navigateUp() = viewModelScope.launch { _sideEffects.emit(FilteringThreeSideEffect.NavigateUp) }

}