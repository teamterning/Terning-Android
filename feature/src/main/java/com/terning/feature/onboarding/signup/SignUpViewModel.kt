package com.terning.feature.onboarding.signup

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.terning.core.designsystem.theme.TerningMain
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

    fun fetchName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    private val helper : String = HELPER
    fun getHelper() = helper

    val color : Color = TerningMain
    fun getHelperColor() = color

    val icon : Int = R.drawable.ic_warning
    fun getHelperIcon() = icon

    // 컬러 가능

    companion object{
        const val NAME_PATTERN = "[!@#\$%^&*(),.?\":{}|<>\\[\\]\\\\/]"
        private const val HELPER = "12자리 이내, 문자/숫자 가능, 특수문자/기호 입력불가"
        private const val HELPER_ERROR ="이름에 특수문자는 입력할 수 없어요"
        private const val HELPER_AVAILABLE = "이용 가능한 이름이에요"
    }
}
