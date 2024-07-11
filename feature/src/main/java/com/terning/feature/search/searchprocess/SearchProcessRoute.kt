package com.terning.feature.search.searchprocess

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.feature.R

@Composable
fun SearchProcessRoute(
    navController: NavHostController,
) {
    SearchProcessScreen(
        navController = navController,
    )
}

@Composable
fun SearchProcessScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SearchProcessViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(
                title = stringResource(
                    id =
                    if (state.showSearchResults) R.string.search_process_result_top_bar_title
                    else R.string.search_process_top_bar_title
                ),
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
            if (!state.showSearchResults) {
                Text(
                    text = stringResource(id = R.string.search_process_question_text),
                    style = TerningTheme.typography.heading2,
                    color = TerningMain,
                    modifier = Modifier.padding(
                        vertical = 16.dp
                    )
                )
            }

            SearchTextField(
                text = state.text,
                onValueChange = { newText ->
                    viewModel.updateText(newText)
                },
                hint = stringResource(R.string.search_text_field_hint),
                leftIcon = R.drawable.ic_nav_search,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .focusRequester(focusRequester)
                    .addFocusCleaner(focusManager),
                onDoneAction = {
                    viewModel.updateQuery(state.text)
                    viewModel.updateShowSearchResults(true)
                }
            )

            if (state.showSearchResults) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 87.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_nosearch),
                        contentDescription = stringResource(
                            id = R.string.search_process_no_result_icon
                        )
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = state.query,
                            style = TerningTheme.typography.body1,
                            color = TerningMain,
                        )
                        Text(
                            text = stringResource(id = R.string.search_process_no_result_text),
                            style = TerningTheme.typography.body1,
                            color = Grey400,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchProcessScreenPreview() {
    TerningPointTheme {
        SearchProcessScreen(
            navController = rememberNavController()
        )
    }
}
