package com.terning.feature.search.search

import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import com.terning.core.state.UiState
import com.terning.domain.entity.search.SearchPopularAnnouncement
import com.terning.feature.R
import com.terning.feature.search.search.component.ImageSlider
import com.terning.feature.search.search.component.InternListType
import com.terning.feature.search.search.component.SearchInternList
import okhttp3.internal.toImmutableList

const val ADVERTISEMENT_URL =
    "https://www.instagram.com/terning_official?igsh=NnNma245bnUzbWNm&utm_source=qr"

@Composable
fun SearchRoute(
    modifier: Modifier,
    navigateToSearchProcess: () -> Unit,
    navigateToIntern: (Long) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val viewState by viewModel.viewState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
    val scrapState by viewModel.scrapState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

    val amplitudeTracker = LocalTracker.current

    LaunchedEffect(key1 = true) {
        viewModel.getSearchViews()
        viewModel.getSearchScraps()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SearchSideEffect.Toast -> {
                        sideEffect.message
                    }
                }
            }
    }

    val searchViewsList = when (viewState.searchViewsList) {
        is UiState.Success -> (viewState.searchViewsList as UiState.Success<List<SearchPopularAnnouncement>>).data.toImmutableList()
        else -> emptyList()
    }

    val searchScrapsList = when (scrapState.searchScrapsList) {
        is UiState.Success -> (scrapState.searchScrapsList as UiState.Success<List<SearchPopularAnnouncement>>).data.toImmutableList()
        else -> emptyList()
    }

    SearchScreen(
        modifier = modifier,
        searchViewsList = searchViewsList,
        searchScrapsList = searchScrapsList,
        navigateToSearchProcess = {
            amplitudeTracker.track(
                type = EventType.CLICK,
                name = "quest_search"
            )
            navigateToSearchProcess()
        },
        navigateToIntern = navigateToIntern,
        onAdvertisementClick = {
            CustomTabsIntent.Builder().build().launchUrl(context, ADVERTISEMENT_URL.toUri())
        }
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewsList: List<SearchPopularAnnouncement>,
    searchScrapsList: List<SearchPopularAnnouncement>,
    navigateToSearchProcess: () -> Unit,
    navigateToIntern: (Long) -> Unit,
    onAdvertisementClick: () -> Unit,
) {

    val images = listOf(
        R.drawable.img_ad_1,
        R.drawable.img_ad_2,
        R.drawable.img_ad_3,
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        TerningImage(
            painter = com.terning.core.R.drawable.ic_terning_logo_typo,
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp)
        )

        Box(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                )
                .noRippleClickable {
                    navigateToSearchProcess()
                }
        ) {
            SearchTextField(
                textStyle = TerningTheme.typography.detail2,
                hint = stringResource(R.string.search_text_field_hint),
                leftIcon = R.drawable.ic_nav_search,
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                readOnly = true,
            )
        }

        LazyColumn {
            item {
                ImageSlider(
                    images = images,
                    onAdvertisementClick = onAdvertisementClick,
                )

                Spacer(modifier = Modifier.padding(top = 20.dp))

                Text(
                    text = stringResource(id = R.string.search_today_popular),
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
                    style = TerningTheme.typography.title1,
                    color = Black
                )
                SearchInternList(
                    type = InternListType.VIEW,
                    searchViewsList = searchViewsList,
                    searchScrapsList = searchScrapsList,
                    navigateToIntern = navigateToIntern,
                )
                SearchInternList(
                    type = InternListType.SCRAP,
                    searchViewsList = searchViewsList,
                    searchScrapsList = searchScrapsList,
                    navigateToIntern = navigateToIntern,
                )
            }
        }
    }
}
