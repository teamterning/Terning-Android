package com.terning.feature.search.search.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun BannerSlider(
    modifier: Modifier = Modifier,
    searchBanners: ImmutableList<SearchBanner>,
    onBannerClick: (Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pageCount = searchBanners.size
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { Int.MAX_VALUE }
    )
    var autoScroll by remember { mutableStateOf(true) }

    LaunchedEffect(autoScroll) {
        while (autoScroll) {
            delay(BANNER_DELAY)
            coroutineScope.launch {
                val nextPage = pagerState.currentPage + 1
                pagerState.animateScrollToPage(
                    page = nextPage,
                    animationSpec = tween(durationMillis = BANNER_ANIMATION_DURATION)
                )
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
                    modifier = Modifier,
                    beyondViewportPageCount = 1,
                    key = { it }
                ) { currentPage ->
                    val pageIndex = currentPage % pageCount
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(searchBanners[pageIndex].imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(112.dp)
                            .noRippleClickable {
                                autoScroll = false
                                onBannerClick(pageIndex)
                                coroutineScope.launch {
                                    delay(BANNER_DELAY)
                                    autoScroll = true
                                }
                            },
                        contentScale = ContentScale.Crop,
                    )
                }

                DotsIndicator(
                    pageCount = pageCount,
                    pageIndex = pagerState.currentPage
                )
            }
        }
    }
}

private const val BANNER_DELAY = 2500L
private const val BANNER_ANIMATION_DURATION = 1000