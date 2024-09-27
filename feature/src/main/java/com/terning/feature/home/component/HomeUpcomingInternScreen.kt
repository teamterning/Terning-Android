package com.terning.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.designsystem.component.item.ScrapBox
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningSub3
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.home.HomeUpcomingIntern
import com.terning.feature.R
import com.terning.feature.dialog.detail.ScrapDialog
import com.terning.feature.home.HomeState

@Composable
fun HomeUpcomingInternScreen(
    internList: List<HomeUpcomingIntern.HomeUpcomingInternDetail>,
    homeState: HomeState,
    navigateToIntern: (Long) -> Unit,
    updateUpcomingDialogVisibility: (Boolean) -> Unit,
    getHomeUpcomingInternList: () -> Unit,
) {
    var selectedInternItem: HomeUpcomingIntern.HomeUpcomingInternDetail? by remember {
        mutableStateOf(null)
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(internList.size) { index ->
            val homeUpcomingIntern = internList[index]

            ScrapBox(
                modifier = Modifier
                    .width(246.dp)
                    .customShadow(
                        color = Grey200,
                        shadowRadius = 5.dp,
                        shadowWidth = 1.dp
                    ),
                cornerRadius = 5.dp,
                scrapColor = Color(android.graphics.Color.parseColor(homeUpcomingIntern.color)),
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .noRippleClickable {
                                selectedInternItem = homeUpcomingIntern
                                updateUpcomingDialogVisibility(true)
                            },
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = homeUpcomingIntern.title,
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    end = 12.dp,
                                    top = 16.dp
                                ),
                            style = TerningTheme.typography.button3,
                            color = Black,
                            maxLines = 3,
                            minLines = 3,
                        )

                        Row(
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    end = 12.dp,
                                    bottom = 12.dp,
                                    top = 8.dp
                                )
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(homeUpcomingIntern.companyImage)
                                    .build(),
                                contentDescription = stringResource(id = R.string.home_company_image),
                                modifier = Modifier
                                    .height(32.dp)
                                    .aspectRatio(1f)
                                    .border(BorderStroke(1.dp, Grey150), CircleShape)
                                    .clip(CircleShape)
                            )

                            Text(
                                text = homeUpcomingIntern.companyInfo,
                                style = TerningTheme.typography.button5,
                                color = Grey500,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(start = 6.dp, end = 16.dp)
                                    .weight(1f)
                            )

                            Row(
                                modifier = Modifier
                                    .size(width = 48.dp, height = 20.dp)
                                    .background(
                                        color = TerningSub3,
                                        shape = RoundedCornerShape(size = 5.dp)
                                    ),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = homeUpcomingIntern.dDay,
                                    style = TerningTheme.typography.body4,
                                    color = TerningMain,
                                )
                            }
                        }
                    }
                }
            )
        }
    }

    if (homeState.homeUpcomingDialogVisibility) {
        val upcomingIntern = selectedInternItem
        with(upcomingIntern) {
            if (this != null) {
                ScrapDialog(
                    title = title,
                    scrapColor = Color(android.graphics.Color.parseColor(color)),
                    deadline = deadline,
                    startYearMonth = startYearMonth,
                    workingPeriod = workingPeriod,
                    internshipAnnouncementId = internshipAnnouncementId,
                    companyImage = companyImage,
                    isScrapped = isScrapped,
                    onDismissRequest = { updateUpcomingDialogVisibility(false) },
                    onClickChangeColor = getHomeUpcomingInternList,
                    onClickNavigateButton = { navigateToIntern(internshipAnnouncementId) }
                )
            }
        }
    }
}
