package com.terning.feature.onboarding.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.terning.feature.dialog.DialogContent
import com.terning.feature.dialog.TerningDialog
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.extension.toast
import com.terning.feature.R
import com.terning.feature.onboarding.signin.component.KakaoButton
import com.terning.feature.onboarding.signup.navigation.navigateSignUp

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val signInState by viewModel.signInState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

    LaunchedEffect(viewModel.signInSideEffects, lifecycleOwner) {
        viewModel.signInSideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignInSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is SignInSideEffect.NavigateToHome -> navController.navigateSignUp()
                }
            }
    }

    SignInScreen(
        onSignInClick = { viewModel.startKakaoLogIn(context) }
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit = {},
) {
    val openDialog = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_terning_point),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp),
        )
        KakaoButton(
            title = stringResource(id = R.string.sign_in_kakao_button),
            onSignInClick = {
//                onSignInClick()
                openDialog.value = true
            },
            modifier = modifier.padding(horizontal = 20.dp)
        )
    }

    when {
        openDialog.value -> {
            TerningDialog(
                onDismissRequest = { openDialog.value = false },
                content = { DialogContent() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    TerningPointTheme {
        SignInScreen()
    }
}