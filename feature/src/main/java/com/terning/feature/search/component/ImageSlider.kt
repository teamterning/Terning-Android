package com.terning.feature.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }
    Column(
        modifier
            .fillMaxWidth()
            .background(Grey200),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .wrapContentSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier.wrapContentSize()
            ) { currentPage ->
                Card(
                    modifier
                        .wrapContentSize()
                ) {
                    Image(
                        painter = painterResource(id = images[currentPage]),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(112.dp)
                            .padding(16.dp)
                    )
                }
            }
            DotsIndicator(
                pageCount = images.size,
                currentPage = pagerState.currentPage
            )
        }
    }
}