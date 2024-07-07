package com.terning.feature.mypage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.response.MockResponseModel

@Composable
fun MyPageRoute(
    viewModel: MyPageViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val state by viewModel.state.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

    LaunchedEffect(key1 = true) {
        viewModel.getFriendsInfo(2)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is MockSideEffect.Toast -> context.toast(sideEffect.message)
                }
            }
    }

    when (state.followers) {
        is UiState.Empty -> {}
        is UiState.Loading -> {}
        is UiState.Failure -> {}
        is UiState.Success -> {
            MockScreen(mockList = (state.followers as UiState.Success<List<MockResponseModel>>).data)
        }
    }
}

@Composable
fun MockScreen(
    mockList: List<MockResponseModel>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        items(mockList) { friend ->
            MockItem(
                name = friend.firstName,
                profileImage = friend.avatar,
                email = friend.email
            )
        }
    }
}
