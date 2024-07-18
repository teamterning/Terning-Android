package com.terning.feature.home.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R

@Composable
fun HomeFilteringScreen(
    grade: String,
    period: String,
    startYear: String,
    onChangeFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(top = 10.dp, bottom = 11.dp)
            .padding(horizontal = 24.dp)
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = modifier
                .background(
                    color = TerningMain,
                    shape = RoundedCornerShape(5.dp),
                )
                .align(Alignment.CenterVertically)
                .noRippleClickable { onChangeFilterClick() },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_home_filtering_28),
                contentDescription = stringResource(id = R.string.home_recommend_filtering),
                modifier = modifier
                    .padding(horizontal = 2.dp),
            )
            Text(
                text = stringResource(id = R.string.home_recommend_filtering),
                style = TerningTheme.typography.button4,
                color = White,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(end = 6.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        HomeFilteringText(
            text = grade,
            modifier = Modifier
                .padding(vertical = 7.dp)
        )
        HomeFilteringDivider()
        HomeFilteringText(
            text = period,
            modifier = Modifier
                .padding(vertical = 7.dp)
        )
        HomeFilteringDivider()
        HomeFilteringText(
            text = startYear,
            modifier = Modifier
                .padding(top = 7.dp, bottom = 7.dp)
        )
    }
}

@Composable
private fun HomeFilteringText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TerningTheme.typography.detail2,
        color = Black,
        modifier = modifier,
    )
}

@Composable
private fun HomeFilteringDivider() {
    VerticalDivider(
        color = Grey300,
        thickness = 2.dp,
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 4.dp),
    )
}