package com.terning.feature.search.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.domain.entity.response.SearchViewsResponseModel
import com.terning.feature.R

@Composable
fun SearchIntern(
    searchViews: SearchViewsResponseModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            10.dp,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                // TODO 효빈 그림자 PR 풀 받아서 바꾸기
                spotColor = Color(0x26DDDDDD),
                ambientColor = Color(0x26DDDDDD)
            )
            .background(
                color = White,
                shape = RoundedCornerShape(size = 5.dp)
            )
            .padding(vertical = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(searchViews.companyImage)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.search_image),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Grey400),

            )
        Text(
            text = searchViews.title,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = TerningTheme.typography.body6,
            color = Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
    }
}