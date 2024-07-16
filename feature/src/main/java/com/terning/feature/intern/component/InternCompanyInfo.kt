package com.terning.feature.intern.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R

private const val MAX_LINES = 2

@Composable
fun InternCompanyInfo(
    modifier: Modifier,
    companyImage: String,
    company: String,
    companyCategory: String,
) {
    Row(
        modifier = modifier.padding(
            start = 20.dp,
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    2.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            width = 1.dp,
                            color = TerningMain,
                            shape = RoundedCornerShape(size = 30.dp)
                        )
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(companyImage)
                            .crossfade(true)
                            .build(),
                        contentDescription = stringResource(id = R.string.search_image),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth()
                            .clip(CircleShape),
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    3.dp,
                    Alignment.Bottom
                ),
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .padding(
                        horizontal = 12.dp
                    )
            ) {
                Text(
                    text = company,
                    style = TerningTheme.typography.title4,
                    color = Black,
                    modifier = modifier.padding(top = 11.dp),
                    maxLines = MAX_LINES,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = companyCategory,
                    style = TerningTheme.typography.body4,
                    color = Grey300,
                    modifier = modifier.padding(bottom = 6.dp)
                )
            }
        }
    }
}