package com.terning.feature.onboarding.filtering

import androidx.lifecycle.ViewModel
import com.terning.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FilteringViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    val name = "남지우"

    private val _state = MutableStateFlow(FilteringState())
    val state: StateFlow<FilteringState> get() = _state.asStateFlow()

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

    fun postFilteringWithServer(){

    }

}