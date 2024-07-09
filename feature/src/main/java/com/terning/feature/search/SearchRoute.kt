package com.terning.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.search.component.ImageSlider

@Composable
fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    var text by remember { mutableStateOf("") }
    val images = listOf(
        R.drawable.ic_nav_search,
        R.drawable.ic_check,
        R.drawable.ic_nav_my_page,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            SearchTextField(
                text = text,
                onValueChange = { newText ->
                    text = newText
                },
                readOnly = true,
                hint = stringResource(R.string.search_text_field_hint),
                leftIcon = R.drawable.ic_nav_search,
            )
        }

        ImageSlider(
            images = images
        )

        Spacer(modifier = Modifier.padding(8.dp))


        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.search_today_popular),
                modifier = Modifier.padding(vertical = 4.dp),
                style = TerningTheme.typography.title1,
                color = Black
            )

            Text(
                text = stringResource(id = R.string.search_most_view_intern),
                style = TerningTheme.typography.title5,
                color = Grey400
            )
        }

    }
}
