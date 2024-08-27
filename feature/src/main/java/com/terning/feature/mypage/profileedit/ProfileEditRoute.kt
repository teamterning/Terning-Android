package com.terning.feature.mypage.profileedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.feature.R

@Composable
fun ProfileEditRoute() {
    ProfileEditScreen()
}

@Composable
fun ProfileEditScreen(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.addFocusCleaner(focusManager)
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = {},
            title = stringResource(id = R.string.profile_edit_title)
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileEditScreenPreview() {
    TerningPointTheme {
        ProfileEditScreen()
    }
}