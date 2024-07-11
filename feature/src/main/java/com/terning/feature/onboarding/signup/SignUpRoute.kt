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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.textfield.NameTextField
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R
import com.terning.feature.onboarding.filtering.navigation.navigateFiltering
import com.terning.core.designsystem.component.bottomsheet.SignUpBottomSheet
import com.terning.feature.onboarding.signup.component.SignUpProfile

@Composable
fun SignUpRoute(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    val signUpState by signUpViewModel.state.collectAsStateWithLifecycle()

    SignUpScreen(
        signUpViewModel = signUpViewModel,
        signUpState = signUpState,
        onButtonClick = { navController.navigateFiltering() }
    )
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    signUpState: SignUpState,
    signUpViewModel: SignUpViewModel,
    onButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
    ) {
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = TerningTheme.typography.heading2,
            modifier = modifier.padding(
                bottom = 42.dp,
                start = 24.dp
            )
        )
        if (showBottomSheet) {
            SignUpBottomSheet(
                onDismiss = { showBottomSheet = false },
                onSaveClick = { showBottomSheet = false }
            )
        }
        Text(
            text = stringResource(id = R.string.sign_up_profile_image),
            style = TerningTheme.typography.body2,
            modifier = modifier
                .padding(
                    start = 24.dp,
                    bottom = 20.dp
                )
        )
        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 52.dp)
        ) {
            SignUpProfile(
                modifier = modifier.noRippleClickable {
                    showBottomSheet = true
                }
            )
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
                    signUpViewModel.isInputValid(name)
                },
                hint = stringResource(id = R.string.sign_up_hint),
                drawLineColor = signUpState.drawLineColor,
                helperMessage = signUpState.helper,
                helperIcon = signUpState.helperIcon,
                helperColor = signUpState.helperColor
            )
        }
        Spacer(modifier = modifier.weight(5f))
        RectangleButton(
            style = TerningTheme.typography.button1,
            paddingVertical = 20.dp,
            text = R.string.sign_up_next_button,
            onButtonClick = { onButtonClick() },
            modifier = modifier.padding(bottom = 12.dp),
            isEnabled = signUpState.isButtonValid
        )
    }
}
