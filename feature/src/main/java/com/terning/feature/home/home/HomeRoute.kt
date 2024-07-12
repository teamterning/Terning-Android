package com.terning.feature.home.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.bottomsheet.SortingBottomSheet
import com.terning.core.designsystem.component.button.SortingButton
import com.terning.core.designsystem.component.item.InternItem
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.feature.R
import com.terning.feature.home.home.component.HomeFilteringScreen
import com.terning.feature.home.home.component.HomeTodayIntern

@Composable
fun HomeRoute() {
    val currentSortBy: MutableState<Int> = remember {
        mutableStateOf(0)
    }

    HomeScreen(currentSortBy)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    currentSortBy: MutableState<Int>,
    modifier: Modifier = Modifier,
) {
    var sheetState by remember { mutableStateOf(false) }

    if (sheetState) {
        SortingBottomSheet(
            onDismiss = {
                sheetState = false
            },
            currentSortBy = currentSortBy.value,
            newSortBy = currentSortBy
        )
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            LogoTopAppBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            contentPadding = PaddingValues(2.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.home_today_title, "남지우"
                        ),
                        modifier = Modifier
                            .padding(top = 11.dp, bottom = 19.dp)
                            .padding(horizontal = 24.dp),
                        style = TerningTheme.typography.title1,
                        color = Black,
                    )
                    HomeTodayIntern()
                }
            }
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(White)
                ) {
                    Text(
                        text = stringResource(id = R.string.home_recommend_sub_title),
                        style = TerningTheme.typography.detail2,
                        color = Black,
                        modifier = Modifier
                            .padding(top = 9.dp)
                            .padding(horizontal = 24.dp),
                    )

                    Text(
                        text = stringResource(id = R.string.home_recommend_main_title),
                        style = TerningTheme.typography.title1,
                        color = Black,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .padding(horizontal = 24.dp),
                    )

                    HomeFilteringScreen(
                        grade = 3,
                        period = 1,
                        startYear = 2024,
                        startMonth = 7,
                    )

                    HorizontalDivider(
                        thickness = 4.dp,
                        color = Grey150,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        SortingButton(
                            sortBy = currentSortBy.value,
                            onCLick = { sheetState = true },
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                        )
                        Spacer(modifier = Modifier.padding(9.dp))
                    }
                }
            }

            items(itemCount) {
                Box(
                    modifier = modifier
                        .height(92.dp)
                        .padding(horizontal = 24.dp)
                        .customShadow(
                            color = Grey200,
                            shadowRadius = 10.dp,
                            shadowWidth = 2.dp
                        )
                        .background(
                            color = White,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    InternItem(
                        imageUrl = "https://reqres.in/img/faces/7-image.jpg",
                        title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용",
                        dateDeadline = "2",
                        workingPeriod = "2",
                        isScraped = false,
                    )
                }
            }
        }
    }
}

private const val itemCount = 10