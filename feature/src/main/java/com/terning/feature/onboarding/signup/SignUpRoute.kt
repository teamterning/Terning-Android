package com.terning.feature.onboarding.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.terning.core.designsystem.component.textfield.NameTextField
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.WarningRed
import com.terning.feature.R
import com.terning.feature.onboarding.signup.component.SignUpProfile

@Composable
fun SignUpRoute(
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    SignUpScreen(
        signUpViewModel = signUpViewModel
    )
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel
) {
    val text by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
//        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = TerningTheme.typography.heading2,
            modifier = modifier.padding(
                bottom = 42.dp,
                start = 24.dp
            )
        )
        Text(
            text = stringResource(id = R.string.sign_up_profile_image),
            style = TerningTheme.typography.body2,
            modifier = modifier.padding(
                start = 24.dp,
                bottom = 20.dp
            )
        )
        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 52.dp)
        ) {
            SignUpProfile()
        }
        Text(
            text = stringResource(id = R.string.sign_up_name),
            modifier = modifier.padding(bottom = 20.dp)
        )
        NameTextField(
            text = text,
            onValueChange = { name ->
                signUpViewModel.fetchName(name)
            },
            hint = stringResource(id = R.string.sign_up_bottom_sheet_description),
            helperMessage = stringResource(
                id = R.string.sign_up_helper
            ),
            helperColor = Grey400
        )

        var text by remember { mutableStateOf("") }

        // TODO 프로필 스크린 TextField로, 삭제될 코드입니다
        var helperMessage by remember { mutableStateOf(R.string.profile_text_field_helper) }
        var helperIcon by remember { mutableStateOf<Int?>(null) }
        var helperColor by remember { mutableStateOf(Grey400) }
        val specialCharacterPattern = Regex("[!@#\$%^&*(),.?\":{}|<>\\[\\]\\\\/]")

        // TODO 프로필 스크린 TextField로, 삭제될 코드입니다
        fun updateHelper(text: String) {
            helperMessage = when {
                text.isEmpty() -> R.string.profile_text_field_helper
                specialCharacterPattern.containsMatchIn(text) -> R.string.profile_text_field_warning
                text.length <= 12 -> R.string.profile_text_field_check
                else -> R.string.profile_text_field_helper
            }
            helperIcon = when {
                text.isEmpty() -> null
                specialCharacterPattern.containsMatchIn(text) -> R.drawable.ic_warning
                text.length <= 12 -> R.drawable.ic_check
                else -> null
            }
            helperColor = when {
                text.isEmpty() -> Grey400
                specialCharacterPattern.containsMatchIn(text) -> WarningRed
                text.length <= 12 -> TerningMain
                else -> Grey400
            }
        }

        NameTextField(
            text = text,
            onValueChange = { newText ->
                text = newText
                updateHelper(newText)
            },
            hint = stringResource(R.string.profile_text_field_hint),
            helperMessage = stringResource(helperMessage),
            helperIcon = helperIcon,
            helperColor = helperColor
        )

        Spacer(modifier = modifier.weight(2f))
    }
}
