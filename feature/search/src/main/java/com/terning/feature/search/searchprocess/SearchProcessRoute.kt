package com.terning.feature.search.searchprocess

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.extension.addFocusCleaner
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.domain.search.entity.SearchResult
import com.terning.feature.dialog.cancel.ScrapCancelDialog
import com.terning.feature.dialog.detail.ScrapDialog
import com.terning.feature.search.R
import com.terning.feature.search.searchprocess.models.SearchProcessState

@Composable
internal fun SearchProcessRoute(
    paddingValues: PaddingValues,
    viewModel: SearchProcessViewModel = hiltViewModel(),
    navController: NavHostController,
    navigateIntern: (Long) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SearchProcessSideEffect.ShowToast -> context.toast(sideEffect.message)

                    is SearchProcessSideEffect.ScrapUpdate -> {
                        sideEffect.keyword
                    }

                    is SearchProcessSideEffect.NavigateIntern -> {
                        navigateIntern(sideEffect.internshipAnnouncementId)
                    }
                }
            }
    }

    SearchProcessScreen(
        paddingValues = paddingValues,
        navigateToIntern = { viewModel.navigateIntern(it) },
        navigateToBack = { navController.navigateUp() },
        state = state,
        updateText = {
            viewModel.updateText(it)
        },
        onSearchAction = {
            viewModel.updateQuery(state.text)
            viewModel.getSearchListFlow()
            viewModel.updateShowSearchResults(true)
            viewModel.updateExistSearchResults()
        },
        onSortButtonClick = {
            viewModel.updateSheetVisible(true)
        },
        onDismissScrapDialog = { isScrapped, searchResult ->
            viewModel.updateScrapDialogVisible(false)
            viewModel.updateSearchResultScrapStatus(
                searchResult.internshipAnnouncementId,
                isScrapped
            )
        },
        onDismissCancelDialog = { isScrapped, searchResult ->
            viewModel.updateScrapDialogVisible(false)
            viewModel.updateSearchResultScrapStatus(
                searchResult.internshipAnnouncementId,
                !isScrapped
            )
        },
        onDismissSheet = {
            viewModel.updateSheetVisible(false)
        },
        onScrapButtonClicked = {
            viewModel.updateScrapDialogVisible(true)
            viewModel.updateSearchResult(
                internshipId = it.internshipAnnouncementId,
                title = it.title,
                dDay = it.dDay,
                deadline = it.deadline,
                startYearMonth = it.startYearMonth,
                workingPeriod = it.workingPeriod,
                companyImage = it.companyImage,
                isScrapped = it.isScrapped,
                color = it.color,
                totalCount = it.totalCount
            )
        },
        onSortChange = {
            viewModel.updateSortBy(it)
        },
        viewModel = viewModel
    )
}

@Composable
private fun SearchProcessScreen(
    paddingValues: PaddingValues,
    navigateToIntern: (Long) -> Unit,
    navigateToBack: () -> Unit,
    state: SearchProcessState = SearchProcessState(),
    updateText: (String) -> Unit = {},
    onSearchAction: () -> Unit = {},
    onSortButtonClick: () -> Unit = {},
    onDismissCancelDialog: (Boolean, SearchResult) -> Unit,
    onDismissScrapDialog: (Boolean, SearchResult) -> Unit,
    onDismissSheet: () -> Unit = {},
    onScrapButtonClicked: (SearchResult) -> Unit,
    onSortChange: (Int) -> Unit = {},
    viewModel: SearchProcessViewModel,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val currentSortBy = remember { mutableIntStateOf(state.currentSortBy) }
    var isInitialFocusSet by rememberSaveable { mutableStateOf(false) }

    val amplitudeTracker = LocalTracker.current

    val searchResultList = viewModel.internSearchResultFlow.collectAsLazyPagingItems()

    val searchResultTotal = remember(searchResultList.loadState.refresh) {
        if (searchResultList.itemCount > 0) {
            searchResultList[0]?.totalCount ?: 0
        } else {
            0
        }
    }


    LaunchedEffect(Unit) {
        if (!isInitialFocusSet) {
            focusRequester.requestFocus()
            isInitialFocusSet = true
        }
    }


    Column(
        modifier = Modifier
            .background(White)
            .addFocusCleaner(focusManager)
            .padding(paddingValues),
    ) {
        BackButtonTopAppBar(
            title = stringResource(
                id =
                if (state.showSearchResults) R.string.search_process_result_top_bar_title
                else R.string.search_process_top_bar_title
            ),
            onBackButtonClick = navigateToBack,
            modifier = Modifier
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            if (!state.showSearchResults) {
                Text(
                    text = stringResource(id = R.string.search_process_question_text),
                    style = TerningTheme.typography.heading2,
                    color = Grey500,
                    modifier = Modifier.padding(
                        top = 14.dp,
                        bottom = 17.dp
                    )
                )
            }

            SearchTextField(
                text = state.text,
                onValueChange = { newText ->
                    updateText(newText)
                },
                hint = stringResource(R.string.search_text_field_hint),
                leftIcon = R.drawable.ic_search_18,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .addFocusCleaner(focusManager),
                onSearchAction = onSearchAction
            )

            if (state.showSearchResults) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row {
                            Text(
                                text = stringResource(id = R.string.search_process_total),
                                style = TerningTheme.typography.detail1,
                                color = Grey400,
                            )
                            Spacer(modifier = Modifier.padding(start = 3.dp))
                            Text(
                                text = searchResultTotal.toString(),
                                style = TerningTheme.typography.button3,
                                color = TerningMain,
                            )
                            Text(
                                text = stringResource(id = R.string.search_process_count),
                                style = TerningTheme.typography.detail1,
                                color = Grey400,
                            )
                        }
                        Row {
                            SortingButton(
                                sortBy = state.currentSortBy,
                                onCLick = onSortButtonClick,
                            )
                        }
                    }

                    if (searchResultList.itemCount > 0) {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                top = 12.dp,
                                bottom = 20.dp,
                            ),
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            items(searchResultList.itemCount, key = { it }) { index ->
                                searchResultList[index]?.run {
                                    SearchResultInternItem(
                                        intern = searchResultList.itemSnapshotList.items[index],
                                        navigateToIntern = navigateToIntern,
                                        onScrapButtonClicked = {
                                            amplitudeTracker.track(
                                                type = EventType.CLICK,
                                                name = "search_scrap"
                                            )
                                            with(searchResultList.itemSnapshotList.items[index]) {
                                                onScrapButtonClicked(this)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        Spacer(
                            modifier = Modifier.padding(top = 40.dp)
                        )
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_home_empty_filtering
                            ),
                            contentDescription = stringResource(
                                id = R.string.search_process_no_result_icon
                            )
                        )
                        Row(
                            modifier = Modifier
                                .padding(
                                    top = 16.dp,
                                    bottom = 6.dp
                                )
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = state.keyword,
                                style = TerningTheme.typography.title4,
                                color = TerningMain,
                                maxLines = MAX_LINES,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f, false)
                            )
                            Text(
                                text = stringResource(id = R.string.search_process_no_result_text_sub),
                                style = TerningTheme.typography.body1,
                                color = Grey400,
                                modifier = Modifier.wrapContentWidth()
                            )
                        }
                        Text(
                            text = stringResource(
                                id = R.string.search_process_no_result_text_main
                            ),
                            style = TerningTheme.typography.body1,
                            color = Grey400,
                        )
                    }
                }
            }
        }
    }

    if (state.sheetState) {
        SortingBottomSheet(
            currentSortBy = state.currentSortBy,
            onDismiss = onDismissSheet,
            newSortBy = currentSortBy,
            onSortChange = onSortChange
        )
    }

    if (state.isScrapDialogVisible) {
        val searchResult = state.searchResult
        if (searchResult.isScrapped) {
            ScrapCancelDialog(
                internshipAnnouncementId = searchResult.internshipAnnouncementId,
                onDismissRequest = { isScrapped ->
                    onDismissCancelDialog(isScrapped, searchResult)
                }
            )
        } else {
            ScrapDialog(
                title = searchResult.title,
                scrapColor = CalRed,
                deadline = searchResult.deadline,
                startYearMonth = searchResult.startYearMonth,
                workingPeriod = searchResult.workingPeriod,
                internshipAnnouncementId = searchResult.internshipAnnouncementId,
                companyImage = searchResult.companyImage,
                isScrapped = false,
                onDismissRequest = { isScrapped ->
                    onDismissScrapDialog(isScrapped, searchResult)
                }
            )
        }
    }
}

@Composable
private fun SearchResultInternItem(
    intern: SearchResult,
    navigateToIntern: (Long) -> Unit,
    onScrapButtonClicked: (Long) -> Unit,
) {
    InternItemWithShadow(
        modifier = Modifier
            .noRippleClickable {
                navigateToIntern(intern.internshipAnnouncementId)
            },
        imageUrl = intern.companyImage,
        title = intern.title,
        dateDeadline = intern.dDay,
        workingPeriod = intern.workingPeriod,
        isScrapped = intern.isScrapped,
        isApplyClosed = intern.dDay == stringResource(id = R.string.intern_apply_closed),
        shadowRadius = 5.dp,
        shadowWidth = 1.dp,
        onScrapButtonClicked = {
            onScrapButtonClicked(intern.internshipAnnouncementId)
        },
    )
}

private const val MAX_LINES = 1