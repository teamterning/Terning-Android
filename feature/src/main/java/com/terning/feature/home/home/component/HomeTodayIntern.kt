package com.terning.feature.home.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.home.HomeTodayIntern
import com.terning.feature.R
import com.terning.feature.home.home.HomeViewModel
import com.terning.feature.home.home.model.HomeDialogState

@Composable
fun HomeTodayIntern(
    internList: List<HomeTodayIntern>,
    homeDialogState: HomeDialogState,
    navigateToIntern: (Long) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(internList.size) { index ->
            HomeTodayInternItem(
                title = internList[index].title,
                scrapColor = Color(android.graphics.Color.parseColor(internList[index].color)),
                modifier = Modifier
                    .noRippleClickable {
                        homeViewModel.updateScrapDialogVisible(true)
                        homeViewModel.updateIsToday(true)
                        homeViewModel.updateSelectColor(
                            Color(
                                android.graphics.Color.parseColor(
                                    internList[index].color
                                )
                            )
                        )
                        selectedIndex = index
                    }
            )
        }
    }

    if (homeDialogState.isScrapDialogVisible && homeDialogState.isToday) {
        TerningBasicDialog(
            onDismissRequest = {
                homeViewModel.updateScrapDialogVisible(false)
                homeViewModel.updatePaletteOpen(false)
            },
            content = {
                with(internList[selectedIndex]) {
                    HomeTodayInternDialog(
                        internInfoList = listOf(
                            stringResource(id = R.string.intern_info_d_day) to deadline,
                            stringResource(id = R.string.intern_info_working) to workingPeriod,
                            stringResource(id = R.string.intern_info_start_date) to startYearMonth,
                        ),
                        navigateTo = {
                            navigateToIntern(internshipAnnouncementId)
                            homeViewModel.updateScrapDialogVisible(false)
                        },
                        homeTodayIntern = internList[selectedIndex],
                    )
                }
            },
        )
    }
}
