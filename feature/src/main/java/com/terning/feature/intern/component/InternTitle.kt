package com.terning.feature.intern.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey375
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningSub3
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R


@Composable
fun InternTitle(
    modifier: Modifier,
    dDay: String,
    title: String,
    viewCount: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = TerningSub3,
                    shape = RoundedCornerShape(size = 5.dp)
                )
                .width(70.dp)
                .height(25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dDay,
                style = TerningTheme.typography.title3,
                color = TerningMain,
            )
        }

        Text(
            text = title,
            style = TerningTheme.typography.heading2,
            color = Black,
            modifier = modifier.padding(
                top = 8.dp,
                bottom = 4.dp
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TerningImage(
                painter = R.drawable.ic_view_count_14
            )
            Text(
                text = stringResource(id = R.string.intern_view_count_detail, viewCount),
                style = TerningTheme.typography.detail2,
                color = Grey375,
            )
        }
    }
}