package com.terning.feature.search.search.component

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Grey200
import com.terning.domain.search.entity.SearchBanner
import kotlinx.coroutines.delay

@Composable
fun ImageSlider(
    modifier: Modifier = Modifier,
    searchBanners: List<SearchBanner>,
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
                delay(2500)
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
        if (searchBanners.isNotEmpty()) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.BottomCenter
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = modifier,
                    beyondViewportPageCount = 1
                ) { currentPage ->
                    val pageIndex = currentPage % searchBanners.size
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(searchBanners[pageIndex].imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(112.dp)
                            .noRippleClickable { onAdvertisementClick(pageIndex) },
                        contentScale = ContentScale.Crop,
                    )
                }
                DotsIndicator(
                    pageCount = searchBanners.size,
                    currentPage = pagerState.currentPage % searchBanners.size
                )
            }
        }
    }
}
