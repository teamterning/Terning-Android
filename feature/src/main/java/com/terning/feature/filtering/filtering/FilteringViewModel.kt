package com.terning.feature.filtering.filtering

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

    private val _sideEffects = MutableSharedFlow<FilteringSideEffect>()
    val sideEffects: SharedFlow<FilteringSideEffect> get() = _sideEffects.asSharedFlow()

    fun fetchGrade(grade: Int) {
        _state.value = _state.value.copy(grade = grade)
    }

    fun fetchWorkingPeriod(workingPeriod: Int) {
        _state.value = _state.value.copy(workingPeriod = workingPeriod)
    }

    fun fetchStartYear(startYear: Int) {
        _state.value = _state.value.copy(startYear = startYear)
    }

    fun fetchStartMonth(startMonth: Int) {
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
                _sideEffects.emit(FilteringSideEffect.NavigateToStartHome)
            }.onFailure {
                _sideEffects.emit(FilteringSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

}