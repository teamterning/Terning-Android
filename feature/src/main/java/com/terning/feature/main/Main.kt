package com.terning.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.terning.core.extension.shortToast
import com.terning.core.ui.theme.TerningAndroidTheme
import com.terning.domain.entity.response.MockResponseModel

@Composable
fun MainScreen(

) {
    TerningAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainRoute()
        }
    }
}

@Composable
fun MainRoute(
    viewModel: MockViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val state by viewModel.state.collectAsStateWithLifecycle(lifecycleOwner = LocalLifecycleOwner.current)

    LaunchedEffect(key1 = true) {
        viewModel.getFriendsInfo(2)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is MockSideEffect.Toast -> context.shortToast(sideEffect.message)
                }
            }
    }

    when (state) {
        is MockState.Empty -> {}
        is MockState.Loading -> {}
        is MockState.Success -> {
            SearchScreen(mockList = (state as MockState.Success).mockList)
        }
    }
}


@Composable
fun SearchScreen(
    mockList: List<MockResponseModel>
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
