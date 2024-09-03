package com.terning.feature.onboarding.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.component.bottomsheet.ProfileBottomSheet
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.item.ProfileWithPlusButton
import com.terning.core.designsystem.component.textfield.NameTextField
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.core.extension.noRippleClickable
import com.terning.core.extension.toast
import com.terning.feature.R

@Composable
fun SignUpRoute(
    authId: String,
    navigateToStartFiltering: (String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val signUpState by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.fetchAuthId(authId)
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is SignUpSideEffect.NavigateToStartFiltering ->
                        navigateToStartFiltering(signUpState.name)
                }
            }
    }

    if (showBottomSheet) {
        ProfileBottomSheet(
            onDismiss = { showBottomSheet = false },
            onSaveClick = { index ->
                showBottomSheet = false
                viewModel.fetchCharacter(index)
            },
            initialSelectedOption = signUpState.character
        )
    }

    SignUpScreen(
        signUpState = signUpState,
        onSignUpClick = {
            viewModel.postSignUpWithServer()
        },
        onInputChange = { name ->
            viewModel.updateName(name)
        },
        onProfileEditClick = { isVisible ->
            showBottomSheet = isVisible
        },
        onValidationChanged = { isVisible ->
            viewModel.updateButtonValidation(isVisible)
        }
    )
}

@Composable
fun SignUpScreen(
    signUpState: SignUpState,
    onSignUpClick: () -> Unit,
    onInputChange: (String) -> Unit,
    onProfileEditClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    onValidationChanged: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
    ) {
        Spacer(modifier = modifier.height(56.dp))
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = TerningTheme.typography.heading2,
            modifier = modifier.padding(start = 24.dp)
        )
        Spacer(modifier = modifier.height(36.dp))
        Text(
            text = stringResource(id = R.string.sign_up_profile_image),
            style = TerningTheme.typography.body2,
            modifier = modifier
                .padding(start = 24.dp),
            color = Grey500
        )
        Spacer(modifier = modifier.height(20.dp))
        Column(
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Spacer(modifier = modifier.height(48.dp))
            ProfileWithPlusButton(
                modifier = modifier.noRippleClickable {
                    onProfileEditClick(true)
                },
                index = signUpState.character
            )
        }
        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 24.dp)
        ) {
            Text(text = stringResource(id = R.string.sign_up_name))
            Spacer(modifier = modifier.height(20.dp))
            NameTextField(
                value = signUpState.name,
                onValueChange = { name ->
                    onInputChange(name)
                },
                hint = stringResource(id = R.string.sign_up_hint),
                onValidationChanged = { isValid -> onValidationChanged(isValid) }
            )
        }
        Spacer(modifier = modifier.weight(1f))
        RectangleButton(
            style = TerningTheme.typography.button1,
            paddingVertical = 20.dp,
            text = R.string.sign_up_next_button,
            onButtonClick = { onSignUpClick() },
            modifier = modifier.padding(bottom = 12.dp),
            isEnabled = signUpState.isButtonValid
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    TerningPointTheme {
        SignUpScreen(
            signUpState = SignUpState(),
            onSignUpClick = {},
            onInputChange = {},
            onProfileEditClick = {},
            onValidationChanged = {}
        )
    }
}