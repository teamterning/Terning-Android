package com.terning.feature.onboarding.signin

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.onboarding.R
import com.terning.feature.onboarding.signin.component.KakaoButton

@Composable
fun SignInRoute(
    navigateToHome: () -> Unit,
    navigateToSignUp: (String) -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
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
                    is SignInSideEffect.NavigateToHome -> navigateToHome()
                    is SignInSideEffect.NavigateSignUp -> navigateToSignUp(sideEffect.authId)
                    is SignInSideEffect.StartKakaoTalkLogin -> startKakoTalkLogIn(context = context) { token, error ->
                        viewModel.signInResult(token = token, error = error)
                    }

                    is SignInSideEffect.StartKakaoWebLogin -> startKakaoWebLogIn(context = context) { token, error ->
                        viewModel.signInResult(token = token, error = error)
                    }

                    is SignInSideEffect.SignInFailure ->
                        signInFailure(
                            context = context,
                            error = sideEffect.error,
                            signInResult = { token, error ->
                                viewModel.signInResult(token = token, error = error)
                            },
                            sigInCancellationOrError = { error ->
                                viewModel.sigInCancellationOrError(error)
                            }
                        )

                }
            }
    }

    SignInScreen(
        onSignInClick = {
            viewModel.startKakaoLogIn(
                isKakaoAvailable = UserApiClient.instance.isKakaoTalkLoginAvailable(context)
            )
        }
    )
}

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 20.dp)
            .padding(bottom = 82.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(modifier = Modifier.weight(1f))
        SignInLottie()
        Spacer(modifier = Modifier.weight(1f))
        KakaoButton(
            title = stringResource(id = R.string.sign_in_kakao_button),
            onSignInClick = onSignInClick,
        )
    }
}

@Composable
private fun SignInLottie() {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.Asset("terning_sign_in.json"))
    LottieAnimation(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(
                (lottieComposition?.bounds
                    ?.width()
                    ?.toFloat()
                    ?: 1f) / (lottieComposition?.bounds?.height() ?: 1)
            ),
        composition = lottieComposition,
    )
}

private fun startKakoTalkLogIn(
    context: Context,
    signInResult: (OAuthToken?, Throwable?) -> Unit,
) {
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        signInResult(token, error)
    }
}

private fun startKakaoWebLogIn(
    context: Context,
    signInResult: (OAuthToken?, Throwable?) -> Unit,
) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        signInResult(token, error)
    }
}

private fun signInFailure(
    context: Context,
    error: Throwable,
    signInResult: (OAuthToken?, Throwable?) -> Unit,
    sigInCancellationOrError: (Throwable) -> Unit
) {
    if (error.toString().contains(KAKAO_NOT_LOGGED_IN)) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            signInResult(token, error)
        }
    } else sigInCancellationOrError(error)
}

private const val KAKAO_NOT_LOGGED_IN = "statusCode=302"

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    TerningPointTheme {
        SignInScreen(
            onSignInClick = {}
        )
    }
}