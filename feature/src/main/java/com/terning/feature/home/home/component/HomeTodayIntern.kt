package com.terning.feature.home.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.feature.intern.navigation.navigateIntern

@Composable
fun HomeTodayIntern(
    internList: List<HomeTodayInternModel>,
    navController: NavHostController,
) {
    val dialogState: MutableState<Boolean> = remember { mutableStateOf(false) }

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
                modifier = Modifier.noRippleClickable {
                    dialogState.value = true
                }
            )

            if (dialogState.value) {
                with(internList[index]) {
                    TerningBasicDialog(
                        onDismissRequest = { dialogState.value = false },
                        content = {
                            HomeTodayInternContent(
                                internInfoList = listOf(
                                    stringResource(id = com.terning.feature.R.string.intern_info_d_day) to deadline,
                                    stringResource(id = com.terning.feature.R.string.intern_info_working) to workingPeriod,
                                    stringResource(id = com.terning.feature.R.string.intern_info_start_date) to startYearMonth,
                                ),
                                homeTodayInternModel = internList[index],
                                announcementId = internList[index].internshipAnnouncementId,
                                navigateTo = { navController.navigateIntern(announcementId = internList[index].internshipAnnouncementId) },
                            )
                        }
                    )
                }
            }
        }
    }
}
