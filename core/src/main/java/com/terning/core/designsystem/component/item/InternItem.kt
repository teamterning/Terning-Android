package com.terning.core.designsystem.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun InternItem(
    imageUrl: String,
    title: String,
    dateDeadline: String,
    workingPeriod: String,
    isScraped: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = title,
            modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .padding(vertical = 10.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        Column(
            modifier
                .padding(
                    top = 10.dp,
                    bottom = 9.dp,
                    start = 8.dp
                )
                .weight(1f),
        ) {
            Text(
                text = "D-" + dateDeadline,
                style = TerningTheme.typography.detail0,
                color = TerningMain,
            )

            Text(
                text = title,
                style = TerningTheme.typography.title5,
                color = Black,
                softWrap = true,
                modifier = modifier.padding(top = 3.dp),
            )

            Row(
                modifier = modifier
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "근무기간",
                    style = TerningTheme.typography.detail3,
                    color = Grey400
                )

                Text(
                    text = workingPeriod,
                    style = TerningTheme.typography.detail3,
                    color = TerningMain,
                    modifier = modifier.padding(start = 4.dp)
                )
            }
        }
        Image(
            imageVector = if(isScraped)
                Icons.Filled.Star
            else
                Icons.Outlined.Star,
            contentDescription = "scrap",
            modifier = modifier
                .padding(end = 2.dp, bottom = 8.dp)
                .align(Alignment.Bottom),
        )
    }
}