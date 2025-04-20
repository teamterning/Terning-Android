package com.terning.feature.onboarding.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.terning.core.analytics.EventType
import com.terning.core.designsystem.component.bottomsheet.ProfileBottomSheet
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.item.ProfileWithPlusButton
import com.terning.core.designsystem.component.textfield.NameTextField
import com.terning.core.designsystem.extension.addFocusCleaner
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.onboarding.R

@Composable
fun SignUpRoute(
    authId: String,
    navigateToStartFiltering: (String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val amplitudeTracker = com.terning.core.analytics.LocalTracker.current

    LaunchedEffect(key1 = true) {
        viewModel.updateAuthId(authId)
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is SignUpSideEffect.NavigateToStartFiltering ->
                        navigateToStartFiltering(state.name)
                }
            }
    }

    if (state.showBottomSheet) {
        ProfileBottomSheet(
            onDismiss = { viewModel.updateBottomSheet(false) },
            onSaveClick = { profileImage ->
                viewModel.updateBottomSheet(false)
                viewModel.updateProfileImage(profileImage.stringValue)
            },
            initialSelectedOption = state.profileImage
        )
    }

    SignUpScreen(
        state = state,
        onSignUpClick = {
            amplitudeTracker.track(
                type = EventType.SIGNUP,
                name = "kakao"
            )
            viewModel.fetchAndSaveFcmToken()
        },
        onInputChange = { name ->
            viewModel.updateName(name)
        },
        onProfileEditClick = { isVisible ->
            viewModel.updateBottomSheet(isVisible)
        },
        onValidationChanged = { isVisible ->
            viewModel.updateButtonValidation(isVisible)
        }
    )
}

@Composable
fun SignUpScreen(
    state: SignUpState,
    onSignUpClick: () -> Unit,
    onInputChange: (String) -> Unit,
    onProfileEditClick: (Boolean) -> Unit,
    onValidationChanged: (Boolean) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(White)
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = TerningTheme.typography.heading2,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = stringResource(id = R.string.sign_up_profile_image),
            style = TerningTheme.typography.body2,
            modifier = Modifier.padding(start = 24.dp),
            color = Grey500
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            ProfileWithPlusButton(
                modifier = Modifier.noRippleClickable {
                    onProfileEditClick(true)
                },
                profileImage = state.profileImage
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 24.dp)
        ) {
            Text(text = stringResource(id = R.string.sign_up_name))
            Spacer(modifier = Modifier.height(20.dp))
            NameTextField(
                value = state.name,
                onValueChange = { name ->
                    onInputChange(name)
                },
                hint = stringResource(id = R.string.sign_up_hint),
                onValidationChanged = { isValid -> onValidationChanged(isValid) }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        RectangleButton(
            style = TerningTheme.typography.button1,
            paddingVertical = 20.dp,
            text = R.string.sign_up_next_button,
            onButtonClick = { onSignUpClick() },
            modifier = Modifier.padding(bottom = 12.dp),
            isEnabled = state.isButtonValid
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    TerningPointTheme {
        SignUpScreen(
            state = SignUpState(),
            onSignUpClick = {},
            onInputChange = {},
            onProfileEditClick = {},
            onValidationChanged = {},
        )
    }
}