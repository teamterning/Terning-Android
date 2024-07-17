package com.terning.feature.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.domain.repository.HomeRepository
import com.terning.feature.R
import com.terning.feature.home.home.model.InternFilterData
import com.terning.feature.home.home.model.UserNameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val _homeSideEffect = MutableSharedFlow<HomeSideEffect>()
    val homeSideEffect get() = _homeSideEffect.asSharedFlow()

    private val _homeTodayState =
        MutableStateFlow<UiState<List<HomeTodayInternModel>>>(UiState.Loading)
    val homeTodayState get() = _homeTodayState.asStateFlow()

    init {
        getHomeTodayInternList()
    }

    private val _userName by mutableStateOf(
        UserNameState(
            userName = "남지우자랑스러운티엘이되",
            internFilter = InternFilterData(
                grade = 1,
                workingPeriod = 1,
                startYear = 2024,
                startMonth = 7,
            )
        )
    )
    val userName get() = _userName

    private val _recommendInternState = MutableStateFlow(0)
    val recommendInternData get() = _recommendInternState.asStateFlow()

    fun setGrade(grade: Int) {
        userName.internFilter?.grade = grade
    }

    fun setWorkingPeriod(workingPeriod: Int) {
        userName.internFilter?.workingPeriod = workingPeriod
    }

    fun getHomeTodayInternList() {
        _homeTodayState.value = UiState.Loading
        viewModelScope.launch {
            homeRepository.getHomeTodayInternList().onSuccess { internList ->
                _homeTodayState.value = UiState.Success(internList)
            }.onFailure { exception: Throwable ->
                _homeTodayState.value = UiState.Failure(exception.message ?: "")
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }
}