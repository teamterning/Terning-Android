package com.terning.feature.searchprocess

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
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

    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(title = "검색") {}
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 24.dp,
                    end = 24.dp
                )
        ) {
            Text(
                text = "어떤 공고를\n찾고 계시나요?",
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
                modifier = modifier.padding(top = 8.dp)
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