package com.terning.feature.mypage.profileedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.domain.entity.mypage.MyPageProfileEdit
import com.terning.domain.repository.MyPageRepository
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
class ProfileEditViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<ProfileEditState> = MutableStateFlow(ProfileEditState())
    val state: StateFlow<ProfileEditState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<ProfileEditSideEffect>()
    val sideEffects: SharedFlow<ProfileEditSideEffect> get() = _sideEffects.asSharedFlow()

    fun navigateUp() = viewModelScope.launch { _sideEffects.emit(ProfileEditSideEffect.NavigateUp) }

    fun updateBottomSheet(isVisible: Boolean) {
        _state.value = _state.value.copy(showBottomSheet = isVisible)
    }

    fun updateInitialInfo(initialName: String, initialProfile: String, authType: String) {
        _state.value = _state.value.copy(
            name = initialName,
            initialName = initialName,
            profile = initialProfile,
            initialProfile = initialProfile,
            authType = authType
        )
    }

    fun updateName(name: String) {
        val isSameAsInitial = name == _state.value.initialName
        val isProfileChangedButNameSame =
            _state.value.profile != _state.value.initialProfile && isSameAsInitial

        _state.value = _state.value.copy(
            name = name,
            initialView = false,
            isProfileChangedButNameSame = isProfileChangedButNameSame
        )
    }

    fun updateProfile(profile: String) {
        _state.value = _state.value.copy(
            profile = profile,
            initialView = false,
            isProfileChangedButNameSame = _state.value.name == _state.value.initialName
        )
    }

    fun updateButtonValidation(isValid: Boolean) {
        _state.value = _state.value.copy(isButtonValid = isValid)
    }

    fun modifyUserInfo() {
        viewModelScope.launch {
            myPageRepository.editProfile(
                MyPageProfileEdit(
                    name = _state.value.name,
                    profileImage = _state.value.profile
                )
            ).onSuccess {
                _sideEffects.emit(ProfileEditSideEffect.NavigateUp)
            }.onFailure {
                _sideEffects.emit(ProfileEditSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

}