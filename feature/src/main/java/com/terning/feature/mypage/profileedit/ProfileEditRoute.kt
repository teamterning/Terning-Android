package com.terning.feature.mypage.profileedit

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.designsystem.component.bottomsheet.SignUpBottomSheet
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.item.ProfileWithPlusButton
import com.terning.core.designsystem.component.textfield.NameTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R

@Composable
fun ProfileEditRoute(
    viewModel: ProfileEditViewModel = hiltViewModel(),
) {
    val profileEditState by viewModel.state.collectAsStateWithLifecycle()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (profileEditState.showBottomSheet) {
        SignUpBottomSheet(
            onDismiss = { showBottomSheet = false },
            onSaveClick = { index ->
                showBottomSheet = false
                viewModel.fetchCharacter(index)
            },
            initialSelectedOption = profileEditState.character
        )
    }
    ProfileEditScreen(
        profileEditState = profileEditState,
        onProfileEditClick = { isVisible ->
            viewModel.showBottomSheet(isVisible)
        },
        onInputChange = { name ->
            viewModel.isInputValid(name)
        },
        onSaveClick = {/*TODO: 수정사항 저장 로직*/ }
    )
}

@Composable
fun ProfileEditScreen(
    modifier: Modifier = Modifier,
    profileEditState: ProfileEditState,
    onProfileEditClick: (Boolean) -> Unit,
    onInputChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier.addFocusCleaner(focusManager)
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = {},
            title = stringResource(id = R.string.profile_edit_title)
        )
        Column(modifier = modifier.fillMaxSize()) {
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.sign_up_profile_image),
                style = TerningTheme.typography.body2,
                modifier = modifier
                    .padding(
                        start = 24.dp,
                        bottom = 20.dp
                    ),
                color = Grey500
            )
            Column(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 48.dp)
            ) {
                ProfileWithPlusButton(
                    modifier = modifier.noRippleClickable {
                        onProfileEditClick(true)
                    },
                    index = profileEditState.character
                )
            }
            Column(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_name),
                    modifier = modifier.padding(bottom = 20.dp),
                    color = Grey500
                )
                NameTextField(
                    value = profileEditState.name,
                    onValueChange = { name ->
                        onInputChange(name)
                    },
                    hint = stringResource(id = R.string.sign_up_hint),
                    drawLineColor = profileEditState.drawLineColor,
                    helperMessage = profileEditState.helper,
                    helperIcon = profileEditState.helperIcon,
                    helperColor = profileEditState.helperColor
                )
            }
            Spacer(modifier = modifier.weight(5f))
            RectangleButton(
                style = TerningTheme.typography.button1,
                paddingVertical = 20.dp,
                text = R.string.profile_edit_save,
                onButtonClick = { onSaveClick() },
                modifier = modifier.padding(bottom = 12.dp),
                isEnabled = profileEditState.isButtonValid
            )
        }
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
            onSaveClick = {}
        )
    }
}