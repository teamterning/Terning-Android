package com.terning.feature.home.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.feature.R

@Composable
fun HomeTodayEmptyWithImg(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 19.dp)
            .padding(horizontal = 24.dp)
            .customShadow(
                color = Grey200,
                shadowRadius = 5.dp,
                shadowWidth = 2.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(5.dp)
            ),
        verticalArrangement = Arrangement.Center
    ) {
        TerningImage(
            painter = R.drawable.ic_home_scrap_empty,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp)
        )
        Text(
            text = stringResource(id = R.string.home_scrap_empty),
            modifier = modifier
                .padding(top = 8.dp, bottom = 25.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = TerningTheme.typography.detail3,
            color = Grey500,
        )
    }
}