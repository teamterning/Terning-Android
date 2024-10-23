package com.terning.feature.search.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.search.SearchAdvertisement
import kotlinx.coroutines.delay

@Composable
fun ImageSlider(
    modifier: Modifier = Modifier,
    images: List<SearchAdvertisement>,
    onAdvertisementClick: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { Int.MAX_VALUE }
    )
    val autoScroll = remember { mutableStateOf(true) }

    LaunchedEffect(autoScroll.value) {
        if (autoScroll.value) {
            while (true) {
                delay(3000)
                if (!pagerState.isScrollInProgress) {
                    val nextPage = pagerState.currentPage + 1
                    pagerState.animateScrollToPage(nextPage)
                }
            }
        }
    }

    Column(
        modifier
            .fillMaxWidth()
            .background(Grey200),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.BottomCenter
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier,
                beyondViewportPageCount = 1
            ) { currentPage ->
                val pageIndex = currentPage % images.size
                Image(
                    painter = painterResource(id = images[pageIndex].imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(112.dp)
                        .noRippleClickable { onAdvertisementClick(pageIndex) },
                    contentScale = ContentScale.Crop,
                )
            }
            DotsIndicator(
                pageCount = images.size,
                currentPage = pagerState.currentPage % images.size
            )
        }
    }
}
