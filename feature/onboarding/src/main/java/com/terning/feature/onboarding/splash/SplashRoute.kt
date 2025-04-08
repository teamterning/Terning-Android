package com.terning.feature.onboarding.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.extension.getVersionName
import com.terning.core.designsystem.extension.launchPlayStore
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.domain.update.entity.UpdateState
import com.terning.feature.onboarding.R
import com.terning.feature.onboarding.splash.component.TerningMajorUpdateDialog
import com.terning.feature.onboarding.splash.component.TerningPatchUpdateDialog
import kotlinx.coroutines.launch

@Composable
fun SplashRoute(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = TerningMain
        )
        systemUiController.setNavigationBarColor(
            color = TerningMain
        )
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val updateState by viewModel.updateState.collectAsStateWithLifecycle()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                lifecycleOwner.lifecycleScope.launch {
                    val version = context.getVersionName()
                    with(viewModel) {
                        showSplash()
                        checkUpdateState(version)
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SplashState.HasAccessToken -> {
                        if (sideEffect.hasAccessToken) navigateToHome()
                        else navigateToSignIn()
                    }
                }
            }
    }

    SplashScreen(
        updateState = updateState,
        onUpdateButtonClick = context::launchPlayStore,
        onUpdateSkipButtonClick = viewModel::checkIfAccessTokenAvailable
    )
}

@Composable
fun SplashScreen(
    updateState: UpdateState,
    onUpdateButtonClick: () -> Unit,
    onUpdateSkipButtonClick: () -> Unit,
) {
    when (updateState) {
        is UpdateState.MajorUpdateAvailable -> {
            AnimatedVisibility(visible = true) {
                TerningMajorUpdateDialog(
                    titleText = updateState.title,
                    bodyText = updateState.content,
                    onUpdateButtonClick = onUpdateButtonClick,
                )
            }
        }

        is UpdateState.PatchUpdateAvailable -> {
            AnimatedVisibility(visible = true) {
                TerningPatchUpdateDialog(
                    titleText = updateState.title,
                    bodyText = updateState.content,
                    onDismissButtonClick = onUpdateSkipButtonClick,
                    onUpdateButtonClick = onUpdateButtonClick,
                )
            }
        }

        else -> {}
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .background(TerningMain),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "splash screen"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TerningPointTheme {
        SplashScreen(
            updateState = UpdateState.NoUpdateAvailable,
            onUpdateButtonClick = {},
            onUpdateSkipButtonClick = {},
        )
    }
}
