package com.terning.feature.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.terning.core.designsystem.component.topappbar.MyPageTopAppBar

@Composable
fun MyPageRoute(
    myPageViewModel: MyPageViewModel = hiltViewModel(),
) {

    MyPageScreen()
}

@Composable
fun MyPageScreen(
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            MyPageTopAppBar(modifier = Modifier)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

