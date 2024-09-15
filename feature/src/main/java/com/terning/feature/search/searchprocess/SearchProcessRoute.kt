package com.terning.feature.search.searchprocess

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.core.extension.noRippleClickable
import com.terning.core.extension.toast
import com.terning.domain.entity.search.SearchResult
import com.terning.feature.R
import com.terning.feature.dialog.cancel.ScrapCancelDialog
import com.terning.feature.dialog.detail.ScrapDialog
import com.terning.feature.intern.navigation.navigateIntern
import com.terning.feature.search.searchprocess.models.SearchProcessState


@Composable
fun SearchProcessRoute(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: SearchProcessViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val internSearchResultData by viewModel.internSearchResultData.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(true) {
        viewModel.getSearchList(
            keyword = state.text,
            page = 0,
            size = 100
        )
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SearchProcessSideEffect.Toast -> context.toast(sideEffect.message)

                    is SearchProcessSideEffect.ScrapUpdate -> {
                        sideEffect.keyword
                    }
                }
            }
    }

    SearchProcessScreen(
        modifier = modifier,
        navigateToIntern = { navController.navigateIntern(it) },
        navigateToBack = { navController.navigateUp() },
        state = state,
        internSearchResultData = internSearchResultData,
        updateText = {
            viewModel.updateText(it)
        },
        onSearchAction = {
            viewModel.updateQuery(state.text)
            viewModel.getSearchList(
                keyword = state.text,
                sortBy = state.currentSortBy,
                page = 0,
                size = 100,
            )
            viewModel.updateShowSearchResults(true)
            viewModel.updateExistSearchResults()
        },
        onSortButtonClick = {
            viewModel.updateSheetVisible(true)
        },
        onDismissCancelDialog = {
            viewModel.getSearchList(
                keyword = state.keyword,
                sortBy = state.sortBy.ordinal,
                page = state.page,
                size = state.size
            )
            viewModel.updateScrapDialogVisible(false)
        },
        onDismissScrapDialog = {
            viewModel.getSearchList(
                keyword = state.keyword,
                sortBy = state.sortBy.ordinal,
                page = state.page,
                size = state.size
            )
            viewModel.updateScrapDialogVisible(false)
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
                color = it.color
            )
        },
        onSortChange = {
            viewModel.updateSortBy(it)
        }
    )
}

@Composable
fun SearchProcessScreen(
    modifier: Modifier = Modifier,
    navigateToIntern: (Long) -> Unit,
    navigateToBack: () -> Unit,
    state: SearchProcessState = SearchProcessState(),
    internSearchResultData: List<SearchResult> = emptyList(),
    updateText: (String) -> Unit = {},
    onSearchAction: () -> Unit = {},
    onSortButtonClick: () -> Unit = {},
    onDismissCancelDialog: (Boolean) -> Unit,
    onDismissScrapDialog: () -> Unit,
    onDismissSheet: () -> Unit = {},
    onScrapButtonClicked: (SearchResult) -> Unit,
    onSortChange: (Int) -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val currentSortBy = remember { mutableIntStateOf(state.currentSortBy) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager),
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
                                text = internSearchResultData.size.toString(),
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

                    if (state.existSearchResults) {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                top = 12.dp,
                                bottom = 20.dp,
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(internSearchResultData.size) { index ->
                                SearchResultInternItem(
                                    intern = internSearchResultData[index],
                                    navigateToIntern = navigateToIntern,
                                    onScrapButtonClicked = {
                                        onScrapButtonClicked(internSearchResultData[index])
                                    }
                                )
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
                    onDismissRequest = onDismissCancelDialog
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
                    onDismissRequest = onDismissScrapDialog,
                )
            }
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
        shadowRadius = 5.dp,
        shadowWidth = 1.dp,
        onScrapButtonClicked = {
            onScrapButtonClicked(intern.internshipAnnouncementId)
        },
    )
}

private const val MAX_LINES = 1