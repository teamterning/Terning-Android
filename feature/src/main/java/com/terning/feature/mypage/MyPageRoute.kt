package com.terning.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.terning.core.designsystem.component.bottomsheet.MyPageLogoutBottomSheet
import com.terning.core.designsystem.component.bottomsheet.MyPageQuitBottomSheet
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.core.state.UiState
import com.terning.feature.R
import com.terning.feature.mypage.component.MyPageItem
import com.terning.feature.mypage.component.MyPageProfile

@Composable
fun MyPageRoute(
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var name by remember { mutableStateOf(state.name) }
    var authType by remember { mutableStateOf(state.authType) }

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
            name = state.name
            authType = state.authType
        }

        is UiState.Loading -> {}
        is UiState.Empty -> {}
        is UiState.Failure -> {}
    }

    if (state.showNotice) {
        viewModel.navigateToNoticeWebView(context)
        viewModel.fetchShowNotice(false)
    }

    if (state.showOpinion) {
        viewModel.navigateToOpinionWebView(context)
        viewModel.fetchShowOpinion(false)
    }

    MyPageScreen(
        onLogoutClick = { viewModel.fetchShowLogoutBottomSheet(true) },
        onQuitClick = { viewModel.fetchShowQuitBottomSheet(true) },
        name = name,
        authType = authType,
        onNoticeClick = { viewModel.fetchShowNotice(true) },
        onOpinionClick = { viewModel.fetchShowOpinion(true) }
    )
}

@Composable
fun MyPageScreen(
    onLogoutClick: () -> Unit,
    onQuitClick: () -> Unit,
    onNoticeClick: () -> Unit,
    onOpinionClick: () -> Unit,
    modifier: Modifier = Modifier,
    name: String = "",
    authType: String = "",
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Back)
    ) {
        UserProfile(
            name = name,
        )
        TerningCommunity(
            onNoticeClick = { onNoticeClick() },
            onOpinionClick = { onOpinionClick() }
        )
        ServiceInfo(
            onNoticeClick = { onNoticeClick() },
            onOpinionClick = { onOpinionClick() }
        )
        Row(
            modifier = Modifier
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
            TerningImage(
                painter = R.drawable.ic_my_page_divider,
                modifier = Modifier.padding(end = 10.dp)
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
    name: String = "남지우",
    modifier: Modifier = Modifier,
    profile: String = "PROFILE_00",
) {
    Row(
        modifier = modifier
    ) {
        MyPageProfile(
            profile = profile
        )
        Column(
            modifier = Modifier
        ) {
            Text(
                text = name,
                style = TerningTheme.typography.title1,
            )
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
                .padding(top = 16.dp, bottom = 20.dp)
        ) {
            MyPageItem(
                text = stringResource(id = R.string.my_page_notice),
                modifier = Modifier.padding(bottom = 6.dp),
                icon = R.drawable.ic_my_page_notice,
                onButtonClick = { onNoticeClick() }
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_opinion),
                modifier = Modifier.padding(bottom = 6.dp),
                icon = R.drawable.ic_my_page_opinion,
                onButtonClick = { onOpinionClick() }
            )
        }
    }
}


@Composable
fun ServiceInfo(
    modifier: Modifier = Modifier,
    onNoticeClick: () -> Unit,
    onOpinionClick: () -> Unit
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
                .padding(top = 16.dp, bottom = 20.dp)
        ) {
            MyPageItem(
                text = stringResource(id = R.string.my_page_notice),
                modifier = Modifier.padding(bottom = 6.dp),
                icon = R.drawable.ic_my_page_notice,
                onButtonClick = { onNoticeClick() }
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_opinion),
                modifier = Modifier.padding(bottom = 6.dp),
                icon = R.drawable.ic_my_page_opinion,
                onButtonClick = { onOpinionClick() }
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_version),
                version = VERSION,
                icon = R.drawable.ic_my_page_version
            )
        }
    }
}

private const val VERSION = "1.0.0"

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    TerningPointTheme {
        MyPageScreen(
            name = "터닝이",
            onNoticeClick = {},
            onOpinionClick = {},
            onLogoutClick = {},
            onQuitClick = {}
        )
    }
}