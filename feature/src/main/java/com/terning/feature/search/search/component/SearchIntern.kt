package com.terning.feature.search.search.component

import InternshipAnnouncement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R
import com.terning.feature.intern.navigation.navigateIntern

@Composable
fun SearchIntern(
    companyImage: String,
    title: String,
    searchViews: InternshipAnnouncement,
    navController: NavHostController,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            10.dp,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(140.dp)
            .background(
                color = White,
                shape = RoundedCornerShape(size = 5.dp)
            )
            .padding(vertical = 8.dp)
            .noRippleClickable {
                navController.navigateIntern()
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(companyImage)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.search_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(76.dp)
                .customShadow(offsetY = 3.dp)
                .wrapContentSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        topEnd = 5.dp
                    )
                )
        )

        Text(
            text = "${searchViews.title} ${searchViews.title}${searchViews.title}${searchViews.title}",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .wrapContentSize(),
            style = TerningTheme.typography.body6,
            color = Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
    }
}