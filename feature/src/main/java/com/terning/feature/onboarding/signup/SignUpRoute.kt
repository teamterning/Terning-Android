package com.terning.feature.onboarding.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.designsystem.component.textfield.NameTextField
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.onboarding.signup.component.SignUpProfile

@Composable
fun SignUpRoute(
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val signUpState by signUpViewModel.state.collectAsStateWithLifecycle()

    SignUpScreen(
        signUpViewModel = signUpViewModel,
        signUpState = signUpState
    )
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    signUpState: SignUpState,
    signUpViewModel: SignUpViewModel
) {
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
        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_name),
                modifier = modifier.padding(bottom = 20.dp)
            )
            NameTextField(
                value = signUpState.name,
                onValueChange = { name ->
                    signUpViewModel.fetchName(name)
                },
                hint = stringResource(id = R.string.sign_up_hint),
                drawLineColor = signUpState.drawLineColor,
                helperMessage = "hi",
                //signUpState.helper,
                helperIcon = R.drawable.ic_sign_up_button,
                //signUpState.helperIcon,
                helperColor = signUpState.helperColor
            )
        }
        Spacer(modifier = modifier.weight(2f))
    }
}
