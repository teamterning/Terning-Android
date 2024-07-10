package com.terning.feature.searchresult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.extension.addFocusCleaner
import com.terning.feature.R

@Composable
fun SearchResultRoute(
    navController: NavHostController,
) {
    SearchResultScreen(
        navController = navController

    )
}

@Composable
fun SearchResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    var text by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(
                title = stringResource(id = R.string.search_result_top_bar_title),
                onBackButtonClick = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .addFocusCleaner(focusManager)
        ) {
            SearchTextField(
                text = text,
                onValueChange = { newText ->
                    text = newText
                },
                hint = stringResource(R.string.search_text_field_hint),
                leftIcon = R.drawable.ic_nav_search,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .focusRequester(focusRequester)
                    .addFocusCleaner(focusManager)
            )
        }
    }
}
