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
import androidx.compose.runtime.MutableState
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

private const val MAX_LINES = 1
private const val SORT_BY = "deadlineSoon"

@Composable
fun SearchProcessRoute(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: SearchProcessViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val currentSortBy: MutableState<Int> = remember { mutableIntStateOf(0) }
    val sheetState by viewModel.sheetState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
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
        navigateToIntern = { navController.navigateIntern(announcementId = it) },
        navigateToBack = { navController.navigateUp() },
        currentSortBy = currentSortBy,
        viewModel = viewModel,
        sheetState = sheetState,
        onDismissCancelDialog = { viewModel.updateScrapDialogVisible(false) },
        onDismissScrapDialog = { viewModel.updateScrapDialogVisible(false) },
        onScrapButtonClicked = { id ->
            viewModel.updateScrapDialogVisible(true)
            viewModel.updateScrapped(true)
            viewModel.updateSelectedInternIndex(id)
        },
        onSortButtonClick = { viewModel.toggleSheetState() }
    )
}

@Composable
fun SearchProcessScreen(
    currentSortBy: MutableState<Int>,
    modifier: Modifier = Modifier,
    navigateToIntern: (Long) -> Unit,
    navigateToBack: () -> Unit,
    viewModel: SearchProcessViewModel = hiltViewModel(),
    sheetState: Boolean,
    onDismissCancelDialog: (Boolean) -> Unit,
    onDismissScrapDialog: () -> Unit,
    onScrapButtonClicked: (Long) -> Unit,
    onSortButtonClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val internSearchResultData by viewModel.internSearchResultData.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()
    val selectedInternIndex by viewModel.selectedInternIndex.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(state.text) {
        viewModel.getSearchList(
            keyword = state.text,
            sortBy = SORT_BY,
            page = 0,
            size = 10
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager),
    ) {
        BackButtonTopAppBar(
            title = stringResource(
                id = if (state.showSearchResults) R.string.search_process_result_top_bar_title
                else R.string.search_process_top_bar_title
            ),
            onBackButtonClick = navigateToBack,
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
                onValueChange = { newText -> viewModel.updateText(newText) },
                hint = stringResource(R.string.search_text_field_hint),
                leftIcon = R.drawable.ic_search_18,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .addFocusCleaner(focusManager),
                onSearchAction = {
                    viewModel.getSearchList(
                        keyword = state.text,
                        sortBy = SORT_BY,
                        page = 0,
                        size = 10
                    )
                    viewModel.updateQuery(state.text)
                    viewModel.updateShowSearchResults(true)
                    viewModel.updateExistSearchResults(state.text)
                }
            )

            if (state.showSearchResults) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SearchResultsHeader(
                        internSearchResultData = internSearchResultData,
                        currentSortBy = currentSortBy,
                        onSortButtonClick = onSortButtonClick
                    )

                    if (internSearchResultData.isNotEmpty()) {
                        SearchResultsList(
                            internSearchResultData = internSearchResultData,
                            navigateToIntern = navigateToIntern,
                            onScrapButtonClicked = onScrapButtonClicked
                        )
                    } else {
                        NoResultsView(state.keyword)
                    }
                }
            }
        }

        if (dialogState.isScrapDialogVisible) {
            ScrapDialogContent(
                internSearchResultData = internSearchResultData,
                selectedInternIndex = selectedInternIndex,
                onDismissCancelDialog = onDismissCancelDialog,
                onDismissScrapDialog = onDismissScrapDialog
            )
        }

        if (sheetState) {
            SortingBottomSheet(
                currentSortBy = currentSortBy.value,
                onDismiss = { viewModel.toggleSheetState() }
            )
        }
    }
}

@Composable
private fun SearchResultsHeader(
    internSearchResultData: List<SearchResult>,
    currentSortBy: MutableState<Int>,
    onSortButtonClick: () -> Unit,
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
                sortBy = currentSortBy.value,
                onCLick = onSortButtonClick,
            )
        }
    }
}

@Composable
private fun SearchResultsList(
    internSearchResultData: List<SearchResult>,
    navigateToIntern: (Long) -> Unit,
    onScrapButtonClicked: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 12.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(internSearchResultData.size) { index ->
            SearchResultInternItem(
                intern = internSearchResultData[index],
                navigateToIntern = navigateToIntern,
                onScrapButtonClicked = onScrapButtonClicked,
            )
        }
    }
}

@Composable
private fun NoResultsView(keyword: String) {
    Spacer(modifier = Modifier.padding(top = 40.dp))
    Image(
        painter = painterResource(id = R.drawable.ic_home_empty_filtering),
        contentDescription = stringResource(id = R.string.search_process_no_result_icon)
    )
    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = keyword,
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
        text = stringResource(id = R.string.search_process_no_result_text_main),
        style = TerningTheme.typography.body1,
        color = Grey400,
    )
}

@Composable
private fun ScrapDialogContent(
    internSearchResultData: List<SearchResult>,
    selectedInternIndex: Int,
    onDismissCancelDialog: (Boolean) -> Unit,
    onDismissScrapDialog: () -> Unit,
) {
    val selectedIntern = internSearchResultData.getOrNull(selectedInternIndex) ?: return

    if (selectedIntern.isScrapped) {
        ScrapCancelDialog(
            internshipAnnouncementId = selectedIntern.internshipAnnouncementId,
            onDismissRequest = onDismissCancelDialog
        )
    } else {
        ScrapDialog(
            title = selectedIntern.title,
            scrapColor = CalRed,
            deadline = selectedIntern.deadline,
            startYearMonth = selectedIntern.startYearMonth,
            workingPeriod = selectedIntern.workingPeriod,
            internshipAnnouncementId = selectedIntern.internshipAnnouncementId,
            companyImage = selectedIntern.companyImage,
            isScrapped = false,
            onDismissRequest = onDismissScrapDialog,
        )
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
        onScrapButtonClicked = onScrapButtonClicked,
    )
}