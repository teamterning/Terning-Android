package com.terning.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.domain.entity.response.MockResponseModel
import com.terning.domain.repository.MockRepository
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MockViewModel @Inject constructor(private val repository: MockRepository) : ViewModel() {

    private val _state: MutableStateFlow<MockState> =
        MutableStateFlow(MockState.Empty)
    val state: StateFlow<MockState> get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<MockSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<MockSideEffect> get() = _sideEffect

    fun getFriendsInfo(page: Int) {
        viewModelScope.launch {
            _state.value = MockState.Loading
            repository.getMockList(
                page
            ).onSuccess { response ->
                val mockDataList = response.map { entity ->
                    MockResponseModel(
                        avatar = entity.avatar,
                        email = entity.email,
                        firstName = entity.firstName,
                        lastName = entity.lastName
                    )
                }
                _state.value = MockState.Success(mockDataList)
                _sideEffect.emit(MockSideEffect.Toast(R.string.server_success))
            }.onFailure {
                _sideEffect.emit(MockSideEffect.Toast(R.string.server_failure))
            }
        }
    }
}