package com.terning.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.extension.customShadow
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.home.R

@Composable
fun HomeUpcomingEmptyFilter(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .customShadow(
                color = Grey150,
                shadowRadius = 5.dp,
                shadowWidth = 1.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(5.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TerningImage(
            painter = R.drawable.ic_home_scrap_empty,
            modifier = Modifier
                .padding(top = 23.dp)
                .size(44.dp)
        )
        Text(
            text = stringResource(id = R.string.home_upcoming_no_scrap),
            modifier = Modifier
                .padding(top = 12.dp, bottom = 23.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TerningTheme.typography.detail2,
            color = Grey500,
        )
    }
}