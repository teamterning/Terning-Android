package com.terning.feature.home.home.component

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
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R

@Composable
fun HomeRecommendEmptyIntern(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_logo),
            contentDescription = stringResource(id = R.string.home_recommend_no_intern_description),
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.home_recommend_no_intern),
            style = TerningTheme.typography.body4,
            color = Grey400,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}