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
import kotlinx.coroutines.delay

@Composable
fun ImageSlider(
    modifier: Modifier = Modifier,
    images: List<Int>,
    onAdvertisementClick: () -> Unit,
) {
    val infiniteImages = remember { images + images + images }
    val pagerState = rememberPagerState(
        initialPage = images.size,
        initialPageOffsetFraction = 0f,
        pageCount = { infiniteImages.size }
    )
    val autoScroll = remember { mutableStateOf(true) }

    LaunchedEffect(autoScroll.value) {
        if (autoScroll.value) {
            while (true) {
                delay(3000)
                if (!pagerState.isScrollInProgress) {
                    val nextPage = pagerState.currentPage + 1
                    pagerState.animateScrollToPage(nextPage % infiniteImages.size)
                }
            }
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        val currentPageMod = pagerState.currentPage % images.size
        if (pagerState.currentPage < images.size) {
            pagerState.scrollToPage(pagerState.currentPage + images.size)
        } else if (pagerState.currentPage >= 2 * images.size) {
            pagerState.scrollToPage(pagerState.currentPage - images.size)
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
                beyondViewportPageCount = infiniteImages.size
            ) { currentPage ->
                Image(
                    painter = painterResource(id = infiniteImages[currentPage % images.size]),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(112.dp)
                        .noRippleClickable(onAdvertisementClick),
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
