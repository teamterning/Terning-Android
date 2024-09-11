package com.terning.feature.mypage.mypage

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.terning.core.designsystem.component.bottomsheet.MyPageLogoutBottomSheet
import com.terning.core.designsystem.component.bottomsheet.MyPageQuitBottomSheet
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.feature.R
import com.terning.feature.mypage.component.MyPageProfile
import com.terning.feature.mypage.mypage.component.MyPageItem

@Composable
fun MyPageRoute(
    paddingValues: PaddingValues,
    navigateToProfileEdit: (String, String) -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val systemUiController = rememberSystemUiController()

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
                        state.profileImage
                    )

                    is MyPageSideEffect.ShowToast -> context.toast(sideEffect.message)
                }
            }
    }

    if (state.showLogoutBottomSheet) {
        MyPageLogoutBottomSheet(
            onDismiss = { viewModel.fetchShowLogoutBottomSheet(false) },
            onLogoutClick = {
                viewModel.logoutKakao()
            },
        )
    }

    if (state.showQuitBottomSheet) {
        MyPageQuitBottomSheet(
            onDismiss = { viewModel.fetchShowQuitBottomSheet(false) },
            onQuitClick = {
                viewModel.quitKakao()
            }
        )
    }

    when (state.isLogoutAndQuitSuccess) {
        is UiState.Success -> {
            viewModel.restartApp(context)
        }

        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Failure -> {}
    }

    when (state.isGetSuccess) {
        is UiState.Success -> {
            MyPageScreen(
                paddingValues = paddingValues,
                onEditClick = viewModel::navigateToProfileEdit,
                onLogoutClick = { viewModel.fetchShowLogoutBottomSheet(true) },
                onQuitClick = { viewModel.fetchShowQuitBottomSheet(true) },
                onNoticeClick = { viewModel.fetchShowNotice(true) },
                onOpinionClick = { viewModel.fetchShowOpinion(true) },
                onServiceClick = { viewModel.fetchShowService(true) },
                onPersonalClick = { viewModel.fetchShowPersonal(true) },
                name = state.name,
                profileImage = state.profileImage
            )
        }

        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Failure -> {
            MyPageScreen(
                paddingValues = paddingValues,
                profileImage = state.profileImage,
            )
        }
    }

    if (state.showNotice) {
        viewModel.navigateToNoticeWebView(context)
        viewModel.fetchShowNotice(false)
    }

    if (state.showOpinion) {
        viewModel.navigateToOpinionWebView(context)
        viewModel.fetchShowOpinion(false)
    }

    if (state.showService) {
        viewModel.navigateToServiceWebView(context)
        viewModel.fetchShowService(false)
    }

    if (state.showPersonal) {
        viewModel.navigateToPersonalWebView(context)
        viewModel.fetchShowPersonal(false)
    }

}

@Composable
fun MyPageScreen(
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
        TerningCommunity(
            onNoticeClick = onNoticeClick,
            onOpinionClick = onOpinionClick
        )
        ServiceInfo(
            onServiceClick = onServiceClick,
            onPersonalClick = onPersonalClick
        )
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
fun UserProfile(
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
fun TerningCommunity(
    modifier: Modifier = Modifier,
    onNoticeClick: () -> Unit,
    onOpinionClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                top = 8.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 20.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 20.dp,
                    start = 16.dp,
                    end = 9.dp
                )
        ) {
            Text(
                text = stringResource(id = R.string.my_page_terning_community),
                style = TerningTheme.typography.body6,
                color = Grey400,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_notice),
                icon = R.drawable.ic_my_page_notice,
                onButtonClick = onNoticeClick
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 20.dp),
                thickness = 1.dp,
                color = Grey150
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_opinion),
                icon = R.drawable.ic_my_page_opinion,
                onButtonClick = onOpinionClick
            )
        }
    }
}

@Composable
fun ServiceInfo(
    modifier: Modifier = Modifier,
    onServiceClick: () -> Unit,
    onPersonalClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 16.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 20.dp,
                    start = 16.dp,
                    end = 9.dp
                )
        ) {
            Text(
                text = stringResource(id = R.string.my_page_service_info),
                style = TerningTheme.typography.body6,
                color = Grey400,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_service),
                icon = R.drawable.ic_my_page_service,
                onButtonClick = onServiceClick
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 20.dp),
                thickness = 1.dp,
                color = Grey150
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_personal),
                icon = R.drawable.ic_my_page_personal,
                onButtonClick = onPersonalClick
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 20.dp),
                thickness = 1.dp,
                color = Grey150
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_version),
                version = VERSION,
                icon = R.drawable.ic_my_page_version
            )
        }
    }
}

private const val VERSION = "1.1.0"

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
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