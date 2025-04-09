package com.terning.feature.search.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.terning.core.designsystem.R.drawable.ic_terning_intern_item_image_loading_popular
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey100
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.search.R

@Composable
fun SearchIntern(
    companyImage: String,
    title: String,
    announcementId: Long,
    navigateToIntern: (Long) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(140.dp)
            .background(
                color = White,
                shape = RoundedCornerShape(size = 5.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(size = 5.dp),
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Grey150,
                        Grey150
                    )
                )
            )
            .padding(top = 8.dp)
            .noRippleClickable {
                navigateToIntern(announcementId)
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(companyImage)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.search_image),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(76.dp)
                .wrapContentSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        topEnd = 5.dp
                    )
                ),
            placeholder = painterResource(ic_terning_intern_item_image_loading_popular),
        )
        HorizontalDivider(
            color = Grey100,
            modifier = Modifier.padding(0.dp),
            thickness = 2.dp
        )
        Column {
            ThreeLineHeightText(
                text = title,
                style = TerningTheme.typography.body5
            )
        }
    }
}


@Composable
fun ThreeLineHeightText(
    text: String,
    style: TextStyle,
) {
    val threeLineHeight = with(LocalDensity.current) {
        (style.lineHeight.toDp() * 4) - style.fontSize.toDp()
    }

    Text(
        text = text,
        modifier = Modifier
            .padding(
                start = 8.dp,
                bottom = 9.dp,
                top = 10.dp,
                end = 8.dp
            )
            .height(threeLineHeight)
            .fillMaxWidth(),
        textAlign = TextAlign.Start,
        style = style,
        color = Black,
        overflow = TextOverflow.Ellipsis,
        maxLines = 3
    )
}