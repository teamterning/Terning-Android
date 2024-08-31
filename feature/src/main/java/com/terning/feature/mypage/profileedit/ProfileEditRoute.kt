package com.terning.feature.mypage.profileedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.addFocusCleaner
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R

@Composable
fun ProfileEditRoute(
    navigateUp: () -> Unit,
    viewModel: ProfileEditViewModel = hiltViewModel(),
    initialName: String,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.updateName(initialName)
    }

    if (state.showBottomSheet) {
        ProfileBottomSheet(
            onDismiss = { viewModel.updateBottomSheet(false) },
            onSaveClick = { index ->
                viewModel.updateBottomSheet(false)
                viewModel.updateCharacter(index)
            },
            initialSelectedOption = state.character
        )
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is ProfileEditSideEffect.NavigateUp -> navigateUp()
                }
            }
    }

    ProfileEditScreen(
        profileEditState = state,
        onProfileEditClick = { isVisible ->
            viewModel.updateBottomSheet(isVisible)
        },
        onInputChange = { editName ->
            viewModel.updateName(editName)
        },
        onSaveClick = {/*TODO: 수정사항 저장 로직*/ },
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
    modifier: Modifier = Modifier,
    onValidationChanged: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = { onBackButtonClick() },
            title = stringResource(id = R.string.profile_edit_title)
        )
        Spacer(modifier = modifier.weight(1f))
        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_profile_image),
                style = TerningTheme.typography.body2,
                color = Grey500
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProfileWithPlusButton(
                modifier = modifier
                    .noRippleClickable {
                        onProfileEditClick(true)
                    }
                    .align(Alignment.CenterHorizontally),
                index = profileEditState.character
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
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(id = R.string.profile_edit_auth_type),
                style = TerningTheme.typography.body2,
                color = Grey500,
            )
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = profileEditState.authType,
                style = TerningTheme.typography.detail0
            )
        }
        Spacer(modifier = modifier.weight(5f))
        RectangleButton(
            style = TerningTheme.typography.button1,
            paddingVertical = 20.dp,
            text = R.string.profile_edit_save,
            onButtonClick = { onSaveClick() },
            isEnabled = profileEditState.isButtonValid
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

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