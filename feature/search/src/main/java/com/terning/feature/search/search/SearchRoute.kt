package com.terning.feature.search.search

import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.domain.search.entity.SearchBanner
import com.terning.domain.search.entity.SearchPopularAnnouncement
import com.terning.feature.search.R
import com.terning.feature.search.search.component.ImageSlider
import com.terning.feature.search.search.component.InternListType
import com.terning.feature.search.search.component.SearchInternList
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
    navigateToSearchProcess: () -> Unit,
    navigateToIntern: (Long) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val bannerState by viewModel.bannerState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
    val viewState by viewModel.viewState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
    val scrapState by viewModel.scrapState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

    val amplitudeTracker = com.terning.core.analytics.LocalTracker.current

    LaunchedEffect(key1 = true) {
        viewModel.getSearchViews()
        viewModel.getSearchScraps()
        viewModel.getSearchBanners()
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SearchSideEffect.ShowToast -> {
                        sideEffect.message
                    }
                }
            }
    }

    val bannerList = when (bannerState.searchBannersList) {
        is UiState.Success -> (bannerState.searchBannersList as UiState.Success<List<SearchBanner>>).data.toImmutableList()
        else -> emptyList<SearchBanner>().toImmutableList()
    }

    val searchViewsList = when (viewState.searchViewsList) {
        is UiState.Success -> (viewState.searchViewsList as UiState.Success<List<SearchPopularAnnouncement>>).data.toImmutableList()
        else -> emptyList<SearchPopularAnnouncement>().toImmutableList()
    }

    val searchScrapsList = when (scrapState.searchScrapsList) {
        is UiState.Success -> (scrapState.searchScrapsList as UiState.Success<List<SearchPopularAnnouncement>>).data.toImmutableList()
        else -> emptyList<SearchPopularAnnouncement>().toImmutableList()
    }

    SearchScreen(
        paddingValues = paddingValues,
        bannerList = bannerList,
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
        onAdvertisementClick = { pageIndex ->
            amplitudeTracker.track(
                type = EventType.CLICK,
                name = "quest_banner"
            )
            CustomTabsIntent.Builder().build()
                .launchUrl(context, bannerList[pageIndex].url.toUri())
        }
    )
}

@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    bannerList: ImmutableList<SearchBanner>,
    searchViewsList: ImmutableList<SearchPopularAnnouncement>,
    searchScrapsList: ImmutableList<SearchPopularAnnouncement>,
    navigateToSearchProcess: () -> Unit,
    navigateToIntern: (Long) -> Unit,
    onAdvertisementClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(White)
            .padding(paddingValues)
    ) {
        TerningImage(
            painter = R.drawable.ic_terning_logo_typo,
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
                    searchBanners = bannerList,
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
