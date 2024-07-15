package com.terning.feature.onboarding.signup

import androidx.lifecycle.ViewModel
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.WarningRed
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> get() = _state.asStateFlow()

    fun isInputValid(name: String) {
        val nameErrorRegex = Regex(NAME_ERROR)
        val trimmedName = if (name.length > MAX_LENGTH) name.substring(0, MAX_LENGTH) else name

        when {
            nameErrorRegex.containsMatchIn(trimmedName) -> _state.value = _state.value.copy(
                name = trimmedName,
                drawLineColor = WarningRed,
                helper = R.string.sign_up_helper_error,
                helperIcon = R.drawable.ic_sign_up_error,
                helperColor = WarningRed,
                isButtonValid = false
            )

            trimmedName.isEmpty() -> _state.value = _state.value.copy(
                name = trimmedName,
                drawLineColor = Grey500,
                helper = R.string.sign_up_helper,
                helperIcon = null,
                helperColor = Grey400,
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

    companion object {
        const val NAME_ERROR = "[!@#\$%^&*(),.?\":{}|<>\\[\\]\\\\/]"
        private const val MAX_LENGTH = 12
    }
}
