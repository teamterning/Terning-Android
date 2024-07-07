package com.terning.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.textfield.NameTextField
import com.terning.core.designsystem.textfield.SearchTextField
import com.terning.feature.R

@Composable
fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        SearchTextField(
            text = text,
            onValueChange = { newText ->
                text = newText
            },
            hint = stringResource(R.string.search_text_field_hint),
            leftIcon = R.drawable.ic_nav_search
        )
        NameTextField(
            text = text,
            onValueChange = { newText ->
                text = newText
            },
            hint = stringResource(R.string.profile_text_field_hint),
            helperMessage = stringResource(R.string.profile_text_field_helper)
        )
    }
}