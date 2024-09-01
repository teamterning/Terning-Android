package com.terning.feature.mypage.profileedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class ProfileEditViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<ProfileEditState> = MutableStateFlow(ProfileEditState())
    val state: StateFlow<ProfileEditState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<ProfileEditSideEffect>()
    val sideEffects: SharedFlow<ProfileEditSideEffect> get() = _sideEffects.asSharedFlow()

    fun updateButtonValidation(isValid: Boolean) {
        _state.value = _state.value.copy(isButtonValid = isValid)
    }

    fun navigateUp() = viewModelScope.launch { _sideEffects.emit(ProfileEditSideEffect.NavigateUp) }

    fun updateBottomSheet(isVisible: Boolean) {
        _state.value = _state.value.copy(showBottomSheet = isVisible)
    }

    fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun updateProfile(profile: Int) {
        _state.value = _state.value.copy(profile = profile)
    }

    fun updateInitialInfo(initialName: String, initialProfile: Int) {
        _state.value = _state.value.copy(
            name = initialName,
            initialName = initialName,
            profile = initialProfile,
            initialProfile = initialProfile
        )
    }

    fun checkIsInfoChange(editName: String, editProfile: Int) {
        val isInfoChanged = editName != _state.value.initialName || editProfile != _state.value.initialProfile
        _state.value = _state.value.copy(
            isInfoChange = isInfoChanged,
            isButtonValid = isInfoChanged && _state.value.isButtonValid
        )
    }

}