package com.terning.feature.mypage.mypage

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.analytics.EventType
import com.terning.core.designsystem.component.bottomsheet.MyPageLogoutBottomSheet
import com.terning.core.designsystem.component.bottomsheet.MyPageQuitBottomSheet
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.mypage.BuildConfig.VERSION_NAME
import com.terning.feature.mypage.R
import com.terning.feature.mypage.mypage.component.MyPageProfile
import com.terning.feature.mypage.mypage.component.MyPageSection
import com.terning.feature.mypage.mypage.model.MyPageUiModel
import com.terning.feature.mypage.mypage.util.MyPageDefaults.NOTICE_URL
import com.terning.feature.mypage.mypage.util.MyPageDefaults.OPINION_URL
import com.terning.feature.mypage.mypage.util.MyPageDefaults.PERSONAL_URL
import com.terning.feature.mypage.mypage.util.MyPageDefaults.SERVICE_URL
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MyPageRoute(
    paddingValues: PaddingValues,
    navigateToProfileEdit: (String, String, String) -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
    restartApp: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val systemUiController = rememberSystemUiController()

    val amplitudeTracker = com.terning.core.analytics.LocalTracker.current

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Back
        )
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            systemUiController.setStatusBarColor(
                color = White
            )
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getProfile()
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is MyPageSideEffect.NavigateToProfileEdit -> navigateToProfileEdit(
                        state.name,
                        state.profileImage,
                        state.authType
                    )

                    is MyPageSideEffect.ShowToast -> context.toast(sideEffect.message)
                    is MyPageSideEffect.NavigateToNoticeWebView -> navigateToNoticeWebView(context)
                    is MyPageSideEffect.NavigateToOpinionWebView -> navigateToOpinionWebView(context)
                    is MyPageSideEffect.NavigateToServiceWebView -> navigateToServiceWebView(context)
                    is MyPageSideEffect.NavigateToPersonalWebView -> navigateToPersonalWebView(
                        context
                    )

                    is MyPageSideEffect.RestartApp -> restartApp()
                }
            }
    }

    if (state.showLogoutBottomSheet) {
        MyPageLogoutBottomSheet(
            onDismiss = { viewModel.fetchShowLogoutBottomSheet(false) },
            onLogoutClick = {
                amplitudeTracker.track(
                    type = EventType.CLICK,
                    name = "mypage_logout"
                )
                viewModel.logoutKakao()
            },
        )
    }

    if (state.showQuitBottomSheet) {
        MyPageQuitBottomSheet(
            onDismiss = { viewModel.fetchShowQuitBottomSheet(false) },
            onQuitClick = {
                amplitudeTracker.track(
                    type = EventType.CLICK,
                    name = "mypage_delete_account"
                )
                viewModel.quitKakao()
            }
        )
    }

    when (state.isGetSuccess) {
        is UiState.Success -> {
            MyPageScreen(
                paddingValues = paddingValues,
                onEditClick = {
                    amplitudeTracker.track(
                        type = EventType.CLICK,
                        name = "mypage_modify_profile"
                    )
                    viewModel.navigateToProfileEdit()
                },
                onLogoutClick = { viewModel.fetchShowLogoutBottomSheet(true) },
                onQuitClick = { viewModel.fetchShowQuitBottomSheet(true) },
                onNoticeClick = {
                    amplitudeTracker.track(
                        type = EventType.CLICK,
                        name = "mypage_notice"
                    )
                    viewModel.fetchShowNotice(true)
                },
                onOpinionClick = {
                    amplitudeTracker.track(
                        type = EventType.CLICK,
                        name = "mypage_comment"
                    )
                    viewModel.fetchShowOpinion(true)
                },
                onServiceClick = { viewModel.fetchShowService(true) },
                onPersonalClick = { viewModel.fetchShowPersonal(true) },
                name = state.name,
                profileImage = state.profileImage
            )
        }

        UiState.Loading -> {}
        UiState.Empty -> {}
        is UiState.Failure -> {
            MyPageScreen(
                paddingValues = paddingValues,
                profileImage = state.profileImage,
            )
        }
    }

    if (state.showNotice) {
        viewModel.fetchShowNotice(false)
    }

    if (state.showOpinion) {
        viewModel.fetchShowOpinion(false)
    }

    if (state.showService) {
        viewModel.fetchShowService(false)
    }

    if (state.showPersonal) {
        viewModel.fetchShowPersonal(false)
    }

}

@Composable
private fun MyPageScreen(
    onEditClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onQuitClick: () -> Unit = {},
    onNoticeClick: () -> Unit = {},
    onOpinionClick: () -> Unit = {},
    onServiceClick: () -> Unit = {},
    onPersonalClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(),
    name: String = "",
    profileImage: String = ""
) {
    val terningCommunityItems = remember {
        persistentListOf(
            MyPageUiModel.Header(text = R.string.my_page_terning_community),
            MyPageUiModel.MyPageItem(
                leadingIcon = R.drawable.ic_my_page_notice,
                text = R.string.my_page_notice,
                onItemClick = onNoticeClick
            ),
            MyPageUiModel.HorizontalDivider,
            MyPageUiModel.MyPageItem(
                leadingIcon = R.drawable.ic_my_page_opinion,
                text = R.string.my_page_opinion,
                onItemClick = onOpinionClick
            )
        )
    }
    val serviceInfoItems = remember {
        persistentListOf(
            MyPageUiModel.Header(text = R.string.my_page_service_info),
            MyPageUiModel.MyPageItem(
                leadingIcon = R.drawable.ic_my_page_service,
                text = R.string.my_page_service,
                onItemClick = onServiceClick
            ),
            MyPageUiModel.HorizontalDivider,
            MyPageUiModel.MyPageItem(
                leadingIcon = R.drawable.ic_my_page_personal,
                text = R.string.my_page_personal,
                onItemClick = onPersonalClick
            ),
            MyPageUiModel.HorizontalDivider,
            MyPageUiModel.MyPageItem(
                leadingIcon = R.drawable.ic_my_page_version,
                text = R.string.my_page_version,
                trailingContent = {
                    Text(
                        text = VERSION_NAME,
                        modifier = Modifier.padding(end = 7.dp),
                        style = TerningTheme.typography.button4,
                        color = Grey350
                    )
                }
            )
        )
    }
    val alarmItems = remember {
        persistentListOf(
            MyPageUiModel.Header(text = R.string.my_page_alarm),
            MyPageUiModel.MyPageItem(
                leadingIcon = R.drawable.ic_my_page_alarm,
                text = R.string.my_page_push_alarm,
                trailingContent = { } // TODO: make toggle button
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Back)
            .padding(paddingValues)
    ) {
        UserProfile(
            name = name,
            profileImage = profileImage,
            onEditClick = onEditClick
        )
        MyPageSection(items = terningCommunityItems)
        MyPageSection(items = serviceInfoItems)
        MyPageSection(items = alarmItems)
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = R.string.my_page_logout),
                style = TerningTheme.typography.button4,
                color = Grey350,
                modifier = Modifier
                    .noRippleClickable {
                        onLogoutClick()
                    }
                    .padding(end = 10.dp)
            )
            VerticalDivider(
                thickness = 1.dp,
                color = Grey350,
                modifier = Modifier.padding(
                    end = 10.dp,
                    top = 2.dp,
                    bottom = 2.dp
                )
            )
            Text(
                text = stringResource(id = R.string.my_page_quit),
                style = TerningTheme.typography.button4,
                color = Grey350,
                modifier = Modifier.noRippleClickable {
                    onQuitClick()
                }
            )
        }
    }
}

@Composable
private fun UserProfile(
    name: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    profileImage: String = "",
) {
    Row(
        modifier = modifier.padding(
            start = 24.dp,
            top = 24.dp,
            bottom = 32.dp
        )
    ) {
        MyPageProfile(profileImage = profileImage)
        Column {
            Text(
                text = name,
                style = TerningTheme.typography.title1,
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 16.dp,
                    bottom = 7.dp
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.noRippleClickable {
                    onEditClick()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.my_page_edit_profile),
                    modifier = Modifier.padding(start = 16.dp, end = 7.dp),
                    style = TerningTheme.typography.button3,
                    color = Grey400
                )
                TerningImage(painter = R.drawable.ic_my_page_go_edit)
            }
        }
    }
}

private fun navigateToNoticeWebView(context: Context) {
    CustomTabsIntent.Builder().build().launchUrl(context, NOTICE_URL.toUri())
}

private fun navigateToOpinionWebView(context: Context) {
    CustomTabsIntent.Builder().build().launchUrl(context, OPINION_URL.toUri())
}

private fun navigateToServiceWebView(context: Context) {
    CustomTabsIntent.Builder().build().launchUrl(context, SERVICE_URL.toUri())
}

private fun navigateToPersonalWebView(context: Context) {
    CustomTabsIntent.Builder().build().launchUrl(context, PERSONAL_URL.toUri())
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    TerningPointTheme {
        MyPageScreen(
            name = "터닝이",
            onNoticeClick = {},
            onOpinionClick = {},
            onLogoutClick = {},
            onQuitClick = {},
            onEditClick = {},
            onServiceClick = {},
            onPersonalClick = {},
        )
    }
}