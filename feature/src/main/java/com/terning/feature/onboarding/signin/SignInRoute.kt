package com.terning.feature.onboarding.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.toast
import com.terning.feature.R
import com.terning.feature.home.home.navigation.navigateHome
import com.terning.feature.onboarding.signin.component.KakaoButton
import com.terning.feature.onboarding.signup.navigation.navigateSignUp

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
        systemUiController.setNavigationBarColor(
            color = White
        )
    }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.signInSideEffects, lifecycleOwner) {
        viewModel.signInSideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignInSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is SignInSideEffect.NavigateToHome -> navController.navigateHome()
                    is SignInSideEffect.NavigateSignUp -> navController.navigateSignUp(sideEffect.authId)
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.weight(1f))
        TerningImage(
            painter = R.drawable.ic_terning_login,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.weight(1f))
        KakaoButton(
            title = stringResource(id = R.string.sign_in_kakao_button),
            onSignInClick = {
                onSignInClick()
            }
        )
        Spacer(modifier = modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    TerningPointTheme {
        SignInScreen()
    }
}