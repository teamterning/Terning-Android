package com.terning.feature.mypage.profileedit

import androidx.lifecycle.ViewModel
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.WarningRed
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<ProfileEditState> = MutableStateFlow(ProfileEditState())
    val state: StateFlow<ProfileEditState> get() = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<ProfileEditSideEffect>()
    val sideEffects: SharedFlow<ProfileEditSideEffect> get() = _sideEffects.asSharedFlow()

    fun isInputValid(name: String) {
        val nameErrorRegex = Regex(NAME_ERROR)
        var trimmedName = ""
        var outOfBoundName = false
        if (name.length > MAX_LENGTH) {
            trimmedName = name.substring(0, MAX_LENGTH)
            outOfBoundName = true
        } else trimmedName = name

        when {
            nameErrorRegex.containsMatchIn(trimmedName) -> _state.value = _state.value.copy(
                name = trimmedName,
                drawLineColor = WarningRed,
                helper = R.string.sign_up_helper_error,
                helperIcon = R.drawable.ic_sign_up_error,
                helperColor = WarningRed,
                isButtonValid = false
            )

            trimmedName.isEmpty() || trimmedName.isBlank() -> _state.value = _state.value.copy(
                name = trimmedName,
                drawLineColor = Grey500,
                helper = R.string.sign_up_helper,
                helperIcon = null,
                helperColor = Grey400,
                isButtonValid = false
            )

            outOfBoundName -> _state.value = _state.value.copy(
                name = trimmedName,
                drawLineColor = WarningRed,
                helper = R.string.sign_up_helper_out,
                helperIcon = R.drawable.ic_sign_up_error,
                helperColor = WarningRed,
                isButtonValid = false
            )

            else -> _state.value = _state.value.copy(
                name = trimmedName,
                drawLineColor = TerningMain,
                helper = R.string.sign_up_helper_available,
                helperIcon = R.drawable.ic_check,
                helperColor = TerningMain,
                isButtonValid = true
            )
        }
    }

    fun showBottomSheet(isVisible: Boolean) {
        _state.value = _state.value.copy(
            showBottomSheet = isVisible
        )
    }

    fun fetchCharacter(character: Int) {
        _state.value = _state.value.copy(character = character)
    }

    companion object {
        const val NAME_ERROR = "[!@#\$%^&*(),.?\":{}|<>\\[\\]\\\\/\\-=+~`\\p{S}\\p{P}]"
        private const val MAX_LENGTH = 12
        private const val KAKA0 = "KAKAO"
    }
}