package com.terning.feature.mypage.mypage

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.bottomsheet.MyPageLogoutBottomSheet
import com.terning.core.designsystem.component.bottomsheet.MyPageQuitBottomSheet
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.mypage.BuildConfig.VERSION_NAME
import com.terning.feature.mypage.R
import com.terning.feature.mypage.mypage.ToggleDefaults.thumbSize
import com.terning.feature.mypage.mypage.ToggleDefaults.toggleHeight
import com.terning.feature.mypage.mypage.ToggleDefaults.toggleHorizontalPadding
import com.terning.feature.mypage.mypage.ToggleDefaults.toggleWidth
import com.terning.feature.mypage.mypage.component.MyPageAlarmDialog
import com.terning.feature.mypage.mypage.component.MyPageProfile
import com.terning.feature.mypage.mypage.component.MyPageSection
import com.terning.feature.mypage.mypage.model.MyPageUiModel
import com.terning.feature.mypage.mypage.util.MyPageDefaults.NOTICE_URL
import com.terning.feature.mypage.mypage.util.MyPageDefaults.OPINION_URL
import com.terning.feature.mypage.mypage.util.MyPageDefaults.PERSONAL_URL
import com.terning.feature.mypage.mypage.util.MyPageDefaults.SERVICE_URL
import kotlinx.collections.immutable.persistentListOf
import kotlin.math.roundToInt


@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MyPageRoute(
    paddingValues: PaddingValues,
    navigateToProfileEdit: (String, String, String) -> Unit,
    restartApp: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val systemUiController = rememberSystemUiController()

    val amplitudeTracker = LocalTracker.current

    var isChecked by remember { mutableStateOf(viewModel.getAlarmAvailability()) }
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val notificationSettingsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            if (!permissionState.status.isGranted) {
                isChecked = false
            }
        }

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
        if (!permissionState.status.isGranted) {
            isChecked = false
        }
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

    if (state.showAlarmDialog) {
        MyPageAlarmDialog(
            onLaterClick = { viewModel.updateAlarmVisibility(false) },
            onSettingClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = ("package:" + context.packageName).toUri()
                }
                notificationSettingsLauncher.launch(intent)
                viewModel.updateAlarmVisibility(false)
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
                onAlarmClick = {
                    val currentAlarmAvailability = viewModel.getAlarmAvailability()

                    if (permissionState.status.isGranted) {
                        viewModel.updateAlarmAvailability(!currentAlarmAvailability)
                        isChecked = !currentAlarmAvailability
                    } else {
                        viewModel.updateAlarmVisibility(true)
                    }
                },
                name = state.name,
                profileImage = state.profileImage,
                isChecked = isChecked
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun MyPageScreen(
    onEditClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onQuitClick: () -> Unit = {},
    onNoticeClick: () -> Unit = {},
    onOpinionClick: () -> Unit = {},
    onServiceClick: () -> Unit = {},
    onPersonalClick: () -> Unit = {},
    onAlarmClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(),
    name: String = "",
    profileImage: String = "",
    isChecked: Boolean = false
) {
    val terningCommunityItems = persistentListOf(
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
    val serviceInfoItems = persistentListOf(
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
    val alarmItems = persistentListOf(
        MyPageUiModel.Header(text = R.string.my_page_alarm),
        MyPageUiModel.MyPageItem(
            leadingIcon = R.drawable.ic_my_page_alarm,
            text = R.string.my_page_push_alarm,
            trailingContent = {
                MyPageToggleButton(
                    check = isChecked,
                    onClick = onAlarmClick
                )
            }
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Back)
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
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
        Spacer(modifier = Modifier.height(100.dp))
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

@Composable
private fun MyPageToggleButton(
    check: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val minBound = with(density) { 0.dp.toPx() }
    val maxBound =
        with(density) { (toggleWidth - thumbSize - toggleHorizontalPadding * 2).toPx() }
    val state by animateFloatAsState(
        targetValue = if (check) maxBound else minBound
    )
    val background = if (check) TerningMain else Grey200

    Row(
        modifier = modifier
            .padding(end = 7.dp)
            .clip(CircleShape)
            .height(toggleHeight)
            .width(toggleWidth)
            .background(background)
            .noRippleClickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .padding(horizontal = toggleHorizontalPadding)
                .offset { IntOffset(state.roundToInt(), 0) }
                .size(thumbSize)
                .clip(CircleShape)
                .background(White)
        )
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

private object ToggleDefaults {
    val toggleHeight: Dp = 20.dp
    val toggleWidth: Dp = 34.dp
    val thumbSize: Dp = 18.dp
    val toggleHorizontalPadding: Dp = 1.dp
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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