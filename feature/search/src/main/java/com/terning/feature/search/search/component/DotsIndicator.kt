package com.terning.feature.search.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.White

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    pagerState: PagerState,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == pagerState.currentPage % pageCount

            Box(
                modifier = modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .size(6.dp)
                    .background(if (isSelected) TerningMain else White)
            )
        }
    }
}