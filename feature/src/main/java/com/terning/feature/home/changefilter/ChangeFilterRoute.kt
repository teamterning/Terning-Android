package com.terning.feature.home.changefilter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.feature.R

@Composable
fun ChangeFilterRoute(
    navController: NavController,
) {
    ChangeFilterScreen(navController)
}

@Composable
fun ChangeFilterScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BackButtonTopAppBar(
                title = stringResource(id = R.string.change_filter_top_bar_title),
                onBackButtonClick = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Text(text = "dd")
        }

    }
}