package com.terning.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.terning.core.designsystem.component.bottomsheet.MyPageLogoutBottomSheet
import com.terning.core.designsystem.component.bottomsheet.MyPageQuitBottomSheet
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.topappbar.MyPageTopAppBar
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R
import com.terning.feature.mypage.component.MyPageItem

@Composable
fun MyPageRoute(
    viewModel: MyPageViewModel = hiltViewModel(),
) {

    var showLogoutBottomSheet by remember { mutableStateOf(false) }
    var showQuitBottomSheet by remember { mutableStateOf(false) }

    if (showLogoutBottomSheet) {
        MyPageLogoutBottomSheet(
            onDismiss = { showLogoutBottomSheet = false },
            onLogoutClick = {
                showLogoutBottomSheet = false
                viewModel.patchLogout()
            },
        )
    }

    if (showQuitBottomSheet) {
        MyPageQuitBottomSheet(
            onDismiss = { showQuitBottomSheet = false },

            )
    }

    MyPageScreen(
        onLogoutClick = { showLogoutBottomSheet = true },
        onQuitClick = { showQuitBottomSheet = true }
    )
}

@Composable
fun MyPageScreen(
    onLogoutClick: () -> Unit,
    onQuitClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            MyPageTopAppBar(modifier = Modifier)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
            Text(
                text = stringResource(id = R.string.my_page_name, "남지우"),
                modifier = Modifier.padding(
                    top = 21.dp,
                    start = 24.dp,
                ),
                style = TerningTheme.typography.heading1,
            )
            Spacer(modifier = Modifier.weight(2f))
            MyPageInfo(
                modifier = Modifier
                    .customShadow(
                        color = Grey200,
                        shadowRadius = 30.dp,
                        shadowWidth = 2.dp
                    )
                    .background(
                        color = Back,
                        shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                    )
            )
            LogoutAndQuit(
                modifier = Modifier.fillMaxWidth(),
                onLogoutClick = { onLogoutClick() },
                onQuitClick = { onQuitClick() }
            )
        }
    }
}

@Composable
fun MyPageInfo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                top = 20.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 16.dp
            )
            .customShadow(
                color = Grey200,
                shadowRadius = 3.dp,
                shadowWidth = 1.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            MyPageItem(
                text = stringResource(id = R.string.my_page_notice),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_send),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            MyPageItem(
                text = stringResource(id = R.string.my_page_version),
                version = VERSION,
            )
        }
    }
}

@Composable
fun LogoutAndQuit(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit,
    onQuitClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Back)
            .padding(bottom = 17.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.my_page_logout),
            style = TerningTheme.typography.button4,
            color = Grey350,
            modifier = modifier.noRippleClickable {
                onLogoutClick()
            }
        )
        Spacer(modifier = Modifier.padding(end = 10.dp))
        TerningImage(painter = R.drawable.ic_my_page_divider)
        Spacer(modifier = Modifier.padding(end = 10.dp))
        Text(
            text = stringResource(id = R.string.my_page_quit),
            style = TerningTheme.typography.button4,
            color = Grey350,
            modifier = modifier.noRippleClickable {
                onQuitClick()
            }
        )
    }
}

private const val VERSION = "1.0.0"