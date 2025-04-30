package com.terning.feature.mypage.profileedit

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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.component.bottomsheet.ProfileBottomSheet
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.item.ProfileWithPlusButton
import com.terning.core.designsystem.component.textfield.NameTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.extension.addFocusCleaner
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.mypage.R

@Composable
fun ProfileEditRoute(
    navigateUp: () -> Unit,
    initialName: String,
    initialProfile: String,
    authType: String,
    viewModel: ProfileEditViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(color = White)
    }

    LaunchedEffect(key1 = true) {
        viewModel.updateInitialInfo(
            initialName = initialName,
            initialProfile = initialProfile,
            authType = authType
        )
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is ProfileEditSideEffect.NavigateUp -> navigateUp()
                    is ProfileEditSideEffect.ShowToast -> context.toast(sideEffect.message)
                }
            }
    }

    if (state.showBottomSheet) {
        ProfileBottomSheet(
            onDismiss = { viewModel.updateBottomSheet(false) },
            onSaveClick = { profileImage ->
                viewModel.updateBottomSheet(false)
                viewModel.updateProfile(profileImage.stringValue)
            },
            initialSelectedOption = state.profile
        )
    }

    ProfileEditScreen(
        profileEditState = state,
        onProfileEditClick = { isVisible ->
            viewModel.updateBottomSheet(isVisible)
        },
        onInputChange = { editName ->
            viewModel.updateName(editName)
        },
        onSaveClick = {
            viewModel.modifyUserInfo()
        },
        name = state.name,
        onBackButtonClick = { viewModel.navigateUp() },
        onValidationChanged = { isValid ->
            viewModel.updateButtonValidation(isValid)
        }
    )
}

@Composable
fun ProfileEditScreen(
    profileEditState: ProfileEditState,
    onProfileEditClick: (Boolean) -> Unit,
    onInputChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    name: String,
    onBackButtonClick: () -> Unit,
    onValidationChanged: (Boolean) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .addFocusCleaner(focusManager)
            .background(White)
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = { onBackButtonClick() },
            title = stringResource(id = R.string.profile_edit_title),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_profile_image),
                style = TerningTheme.typography.body2,
                color = Grey500
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProfileWithPlusButton(
                modifier = Modifier
                    .noRippleClickable {
                        onProfileEditClick(true)
                    }
                    .align(Alignment.CenterHorizontally),
                profileImage = profileEditState.profile
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(id = R.string.sign_up_name),
                color = Grey500
            )
            Spacer(modifier = Modifier.height(20.dp))
            NameTextField(
                value = name,
                onValueChange = { editName ->
                    onInputChange(editName)
                },
                hint = stringResource(id = R.string.sign_up_hint),
                onValidationChanged = { isValid ->
                    onValidationChanged(isValid)
                },
                initialView = profileEditState.initialView,
                isProfileChangedButNameSame = profileEditState.isProfileChangedButNameSame
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(id = R.string.profile_edit_auth_type),
                style = TerningTheme.typography.body2,
                color = Grey500,
            )
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = if (profileEditState.authType == KAKA0) stringResource(id = R.string.profile_edit_kakao)
                else "",
                style = TerningTheme.typography.detail0
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        RectangleButton(
            style = TerningTheme.typography.button1,
            paddingVertical = 20.dp,
            text = R.string.profile_edit_save,
            onButtonClick = onSaveClick,
            isEnabled = profileEditState.isButtonValid,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

private const val KAKA0 = "KAKAO"

@Preview(showBackground = true)
@Composable
fun ProfileEditScreenPreview() {
    TerningPointTheme {
        ProfileEditScreen(
            profileEditState = ProfileEditState(),
            onProfileEditClick = {},
            onInputChange = {},
            onSaveClick = {},
            name = "터닝이",
            onBackButtonClick = {},
            onValidationChanged = {}
        )
    }
}