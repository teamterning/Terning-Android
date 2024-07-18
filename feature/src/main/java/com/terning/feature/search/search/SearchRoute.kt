package com.terning.feature.search.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey100
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable
import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternshipAnnouncementModel
import com.terning.feature.R
import com.terning.feature.search.search.component.ImageSlider
import com.terning.feature.search.search.component.InternListType
import com.terning.feature.search.search.component.SearchInternList
import com.terning.feature.search.searchprocess.navigation.navigateSearchProcess

@Composable
fun SearchRoute(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val viewState by viewModel.viewState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)
    val scrapState by viewModel.scrapState.collectAsStateWithLifecycle(lifecycleOwner = lifecycleOwner)

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
        is UiState.Success -> (viewState.searchViewsList as UiState.Success<List<InternshipAnnouncementModel>>).data
        else -> emptyList()
    }

    val searchScrapsList = when (scrapState.searchScrapsList) {
        is UiState.Success -> (scrapState.searchScrapsList as UiState.Success<List<InternshipAnnouncementModel>>).data
        else -> emptyList()
    }

    SearchScreen(
        navController = navController,
        searchViewsList = searchViewsList,
        searchScrapsList = searchScrapsList
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    searchViewsList: List<InternshipAnnouncementModel>,
    searchScrapsList: List<InternshipAnnouncementModel>,
) {
    val images = listOf(
        R.drawable.img_ad_1,
        R.drawable.img_ad_2,
        R.drawable.img_ad_3,
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            LogoTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp,
                        vertical = 16.dp
                    )
                    .noRippleClickable {
                        navController.navigateSearchProcess()
                    }
            ) {
                SearchTextField(
                    hint = stringResource(R.string.search_text_field_hint),
                    leftIcon = R.drawable.ic_nav_search,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    readOnly = true,
                )
            }

            LazyColumn {
                item {
                    ImageSlider(images = images)

                    Spacer(modifier = Modifier.padding(8.dp))

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
                        navController = navController
                    )
                    HorizontalDivider(
                        thickness = 4.dp,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Grey100,
                    )
                    SearchInternList(
                        type = InternListType.SCRAP,
                        searchViewsList = searchViewsList,
                        searchScrapsList = searchScrapsList,
                        navController = navController
                    )
                }
            }
        }
    }
}
