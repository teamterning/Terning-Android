package com.terning.feature.onboarding.signup

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.feature.R

data class SignUpState(
    val name: String = "",
    val character: Int = 0,
    val drawLineColor: Color = Grey500,
    @StringRes val helper: Int = R.string.sign_up_helper,
    val helperIcon: Int? = null,
    val helperColor: Color = Grey400,
    val isButtonValid: Boolean = false,
    val authId: String = ""
)