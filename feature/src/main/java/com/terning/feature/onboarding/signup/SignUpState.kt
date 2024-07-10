package com.terning.feature.onboarding.signup

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500

data class SignUpState(
    val name: String = "",
    val character: Int = 0,
    val drawLineColor: Color = Grey500,
    val helper: String = "",
    val helperIcon: Int? = null,
    val helperColor: Color = Grey400,
)