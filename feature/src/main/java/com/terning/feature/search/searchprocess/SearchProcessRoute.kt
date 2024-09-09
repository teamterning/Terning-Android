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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.dialog.ScrapCancelDialogContent
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange1
import com.terning.core.designsystem.theme.CalOrange2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.core.extension.noRippleClickable
import com.terning.core.extension.toast
import com.terning.domain.entity.home.HomeRecommendIntern
import com.terning.feature.R
import com.terning.feature.home.home.component.HomeRecommendInternDialog
import com.terning.feature.intern.navigation.navigateIntern

private const val MAX_LINES = 1

@Composable
fun SearchProcessRoute(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: SearchProcessViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val currentSortBy: MutableState<Int> = remember {
        mutableIntStateOf(0)
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
        navController = navController,
        currentSortBy = currentSortBy,
        viewModel = viewModel,
    )
}

@Composable
fun SearchProcessScreen(
    currentSortBy: MutableState<Int>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SearchProcessViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var sheetState by remember { mutableStateOf(false) }
    val internSearchResultData by viewModel.internSearchResultData.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()
    val selectedInternIndex = remember { mutableIntStateOf(-1) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(true) {
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
                id =
                if (state.showSearchResults) R.string.search_process_result_top_bar_title
                else R.string.search_process_top_bar_title
            ),
            onBackButtonClick = { navController.navigateUp() },
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
                    modifier = Modifier.padding(bottom = 17.dp)
                )
            }

            SearchTextField(
                text = state.text,
                onValueChange = { newText ->
                    viewModel.updateText(newText)
                },
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
                    if (internSearchResultData.isNotEmpty()) {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                top = 12.dp,
                                bottom = 20.dp,
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(viewModel.internSearchResultData.value.size) { index ->
                                InternItemWithShadow(
                                    modifier = Modifier.noRippleClickable {
                                        navController.navigateIntern(
                                            announcementId = internSearchResultData[index]
                                                .internshipAnnouncementId
                                        )
                                    },
                                    imageUrl = internSearchResultData[index].companyImage,
                                    title = internSearchResultData[index].title,
                                    dateDeadline = internSearchResultData[index].dDay,
                                    workingPeriod = internSearchResultData[index].workingPeriod,
                                    isScrapped = internSearchResultData[index].scrapId != null,
                                    shadowWidth = 2.dp,
                                    shadowRadius = 10.dp,
                                    onScrapButtonClicked = {
                                        viewModel.updateScrapDialogVisible(true)
                                        viewModel.updateScrapped(scrapped = internSearchResultData[index].scrapId != null)
                                        selectedInternIndex.intValue = index
                                    }
                                )
                            }
                        }
                    } else {
                        Spacer(
                            modifier = Modifier.padding(top = 87.dp)
                        )
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_home_scrap_empty
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
                                style = TerningTheme.typography.body1,
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

        if (dialogState.isScrapDialogVisible) {
            TerningBasicDialog(
                onDismissRequest = { viewModel.updateScrapDialogVisible(false) },
                content = {
                    val selectedIndex = selectedInternIndex.value
                    if (selectedIndex != -1) {
                        val selectedIntern = internSearchResultData[selectedIndex]
                        if (selectedIntern.scrapId != null) {
                            ScrapCancelDialogContent(
                                onClickScrapCancel = {
                                    viewModel.updateScrapDialogVisible(false)
                                    viewModel.deleteScrap(
                                        internSearchResultData[selectedIndex].scrapId ?: -1,
                                    )
                                    if (dialogState.isScrappedState) {
                                        viewModel.getSearchList(
                                            keyword = state.text,
                                            sortBy = SORT_BY,
                                            page = 0,
                                            size = 10
                                        )
                                        viewModel.updateScrapped(false)
                                    }
                                    viewModel.updateSelectColor(CalRed)
                                }
                            )
                        } else {
                            val colorList = listOf(
                                CalRed,
                                CalOrange1,
                                CalOrange2,
                                CalYellow,
                                CalGreen1,
                                CalGreen2,
                                CalBlue1,
                                CalBlue2,
                                CalPurple,
                                CalPink,
                            )

                            val selectedColorIndex =
                                colorList.indexOf(dialogState.selectedColor).takeIf { it >= 0 } ?: 0

                            with(selectedIntern) {
                                HomeRecommendInternDialog(
                                    internInfoList = listOf(
                                        stringResource(id = R.string.intern_info_d_day) to deadline,
                                        stringResource(id = R.string.intern_info_working) to workingPeriod,
                                        stringResource(id = R.string.intern_info_start_date) to startYearMonth,
                                    ),
                                    clickAction = {
                                        if (dialogState.isPaletteOpen) {
                                            viewModel.updatePaletteOpen(false)
                                            viewModel.postScrap(
                                                internshipAnnouncementId = internshipAnnouncementId,
                                                selectedColorIndex,
                                            )
                                            viewModel.updateColorChange(false)
                                            viewModel.updateScrapDialogVisible(false)
                                        } else {
                                            if (dialogState.isColorChange) {
                                                viewModel.postScrap(
                                                    internshipAnnouncementId,
                                                    selectedColorIndex
                                                )
                                                viewModel.updateColorChange(false)
                                            } else {
                                                viewModel.postScrap(
                                                    internshipAnnouncementId,
                                                    colorList.indexOf(dialogState.selectedColor)
                                                )
                                            }
                                            viewModel.updateScrapDialogVisible(false)
                                        }
                                    },
                                    onColorSelected = { newColor ->
                                        viewModel.updateSelectColor(newColor)
                                    },
                                    homeRecommendIntern = HomeRecommendIntern(
                                        scrapId = scrapId,
                                        internshipAnnouncementId = internshipAnnouncementId,
                                        companyImage = companyImage,
                                        title = title,
                                        deadline = deadline,
                                        workingPeriod = workingPeriod,
                                        startYearMonth = startYearMonth,
                                        isScrapped = scrapId != null,
                                        dDay = dDay
                                    ),
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchProcessScreenPreview() {
    TerningPointTheme {}
}

private const val SORT_BY = "deadlineSoon"