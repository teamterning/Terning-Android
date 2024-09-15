package com.terning.feature.search.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import kotlinx.coroutines.delay

@Composable
fun ImageSlider(
    modifier: Modifier = Modifier,
    images: List<Int>,
) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    val autoScroll = remember { mutableStateOf(true) }

    LaunchedEffect(autoScroll.value) {
        if (autoScroll.value) {
            while (true) {
                delay(2000)
                if (!pagerState.isScrollInProgress) {
                    val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
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
                modifier = modifier
            ) { currentPage ->
                Image(
                    painter = painterResource(id = images[currentPage]),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(112.dp),
                    contentScale = ContentScale.Crop
                )
            }
            DotsIndicator(
                pageCount = images.size,
                currentPage = pagerState.currentPage
            )
        }
    }
}