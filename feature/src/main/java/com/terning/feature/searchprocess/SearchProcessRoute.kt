package com.terning.feature.searchprocess

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.feature.R

@Composable
fun SearchProcessRoute() {
    SearchProcessScreen()
}

@Composable
fun SearchProcessScreen(
    modifier: Modifier = Modifier,
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
                title = stringResource(id = R.string.search_process_top_bar_title),
                onBackButtonClick = { },
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
            Text(
                text = stringResource(id = R.string.search_process_question_text),
                style = TerningTheme.typography.heading2,
                color = Grey500,
                modifier = Modifier.padding(
                    vertical = 16.dp
                )
            )
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

@Preview(showBackground = true)
@Composable
fun SearchProcessScreenPreview() {
    TerningPointTheme {
        SearchProcessScreen()
    }
}
