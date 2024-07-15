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
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.item.InternItemWithShadow
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.addFocusCleaner
import com.terning.feature.R
import com.terning.feature.home.home.model.RecommendInternData

private const val MAX_LINES = 1

@Composable
fun SearchProcessRoute(
    navController: NavHostController,
) {
    val currentSortBy: MutableState<Int> = remember {
        mutableIntStateOf(0)
    }
    SearchProcessScreen(
        navController = navController,
        currentSortBy = currentSortBy
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
    val recommendState: List<RecommendInternData> = listOf(
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
            dDay = 22,
            workingPeriod = 2,
            isScrapped = true,
        ),
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "ㅇㄻㅇㅁㄻㄹㅇㅁㅇㄹ",
            dDay = 9,
            workingPeriod = 6,
            isScrapped = false,
        ),
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
            dDay = 2,
            workingPeriod = 4,
            isScrapped = true,
        ),
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
            dDay = 22,
            workingPeriod = 2,
            isScrapped = true,
        ),
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "ㅇㄻㅇㅁㄻㄹㅇㅁㅇㄹ",
            dDay = 9,
            workingPeriod = 6,
            isScrapped = false,
        ),
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
            dDay = 2,
            workingPeriod = 4,
            isScrapped = true,
        ),
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
            dDay = 22,
            workingPeriod = 2,
            isScrapped = true,
        ),
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "ㅇㄻㅇㅁㄻㄹㅇㅁㅇㄹ",
            dDay = 9,
            workingPeriod = 6,
            isScrapped = false,
        ),
        RecommendInternData(
            imgUrl = "https://reqres.in/img/faces/7-image.jpg",
            title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
            dDay = 2,
            workingPeriod = 4,
            isScrapped = true,
        ),
    )
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(
                title = stringResource(
                    id =
                    if (state.showSearchResults) R.string.search_process_result_top_bar_title
                    else R.string.search_process_top_bar_title
                ),
                onBackButtonClick = { navController.navigateUp() },
                modifier = Modifier
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .addFocusCleaner(focusManager),
        ) {
            if (!state.showSearchResults) {
                Text(
                    text = stringResource(id = R.string.search_process_question_text),
                    style = TerningTheme.typography.heading2,
                    color = TerningMain,
                    modifier = Modifier.padding(
                        vertical = 16.dp
                    )
                )
            }

            SearchTextField(
                text = state.text,
                onValueChange = { newText ->
                    viewModel.updateText(newText)
                },
                hint = stringResource(R.string.search_text_field_hint),
                leftIcon = R.drawable.ic_nav_search,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .focusRequester(focusRequester)
                    .addFocusCleaner(focusManager),
                onDoneAction = {
                    viewModel.updateQuery(state.text)
                    viewModel.updateShowSearchResults(true)
                }
            )

            if (state.showSearchResults) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (state.existSearchResults) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            SortingButton(
                                sortBy = currentSortBy.value,
                                onCLick = { sheetState = true },
                            )
                        }
                        LazyColumn(
                            contentPadding = PaddingValues(
                                top = 12.dp,
                                bottom = 20.dp,
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(recommendState.size) { index ->
                                InternItemWithShadow(
                                    imageUrl = recommendState[index].imgUrl,
                                    title = recommendState[index].title,
                                    dateDeadline = recommendState[index].dDay.toString(),
                                    workingPeriod = recommendState[index].workingPeriod.toString(),
                                    isScraped = recommendState[index].isScrapped
                                )
                            }
                        }

                    } else {
                        Spacer(
                            modifier = Modifier.padding(top = 87.dp)
                        )
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_empty_logo
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
                                text = state.query,
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
    }
}

@Preview(showBackground = true)
@Composable
fun SearchProcessScreenPreview() {
    TerningPointTheme {
        SearchProcessScreen(
            navController = rememberNavController(),
            currentSortBy = remember {
                mutableIntStateOf(0)
            }
        )
    }
}
