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

        when {
            nameErrorRegex.containsMatchIn(name) -> _state.value = _state.value.copy(
                name = name,
                drawLineColor = WarningRed,
                helper = HELPER_ERROR,
                helperIcon = R.drawable.ic_sign_up_error,
                helperColor = WarningRed
            )

            name.isEmpty() -> _state.value = _state.value.copy(
                name = name,
                drawLineColor = Grey500,
                helper = HELPER,
                helperIcon = null,
                helperColor = Grey400
            )

            else -> _state.value = _state.value.copy(
                name = name,
                drawLineColor = TerningMain,
                helper = HELPER_AVAILABLE,
                helperIcon = R.drawable.ic_sign_up_available,
                helperColor = TerningMain,
                isButtonValid = true
            )
        }
    }

    companion object {
        const val NAME_ERROR = "[!@#\$%^&*(),.?\":{}|<>\\[\\]\\\\/]"
        const val HELPER = "12자리 이내, 문자/숫자 가능, 특수문자/기호 입력불가"
        private const val HELPER_ERROR = "이름에 특수문자는 입력할 수 없어요"
        private const val HELPER_AVAILABLE = "이용 가능한 이름이에요"
    }
}
