package com.terning.feature.onboarding.filtering

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.feature.onboarding.filtering.component.FilteringStatus

@Composable
fun FilteringOneScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    filteringViewModel: FilteringViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(
                onBackButtonClick = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FilteringStatus()
        }
    }
}


