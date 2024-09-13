package com.terning.feature.home.home.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R

@Composable
fun HomeRecommendEmptyIntern(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_home_empty_filtering),
            contentDescription = stringResource(id = R.string.home_recommend_no_intern_description),
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = text),
            style = TerningTheme.typography.detail2,
            color = Grey400,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 4.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeRecommendInternPreview() {
    TerningPointTheme {
        HomeRecommendEmptyIntern(R.string.home_recommend_no_intern)
    }
}