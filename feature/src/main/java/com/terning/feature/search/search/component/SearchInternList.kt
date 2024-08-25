package com.terning.feature.search.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.domain.entity.search.SearchPopularAnnouncement
import com.terning.feature.R

@Composable
fun SearchInternList(
    type: InternListType,
    searchScrapsList: List<SearchPopularAnnouncement>?,
    searchViewsList: List<SearchPopularAnnouncement>?,
    navigateToIntern: (Long) -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Text(
            text = stringResource(
                id = when (type) {
                    InternListType.VIEW -> R.string.search_most_view_intern
                    InternListType.SCRAP -> R.string.search_most_scrap_intern
                }
            ),
            style = TerningTheme.typography.title5,
            color = Grey400
        )

        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            when (type) {
                InternListType.VIEW -> searchViewsList?.let {
                    items(it.size) { index ->
                        SearchIntern(
                            companyImage = searchViewsList[index].companyImage,
                            title = searchViewsList[index].title,
                            announcementId = searchViewsList[index].announcementId,
                            navigateToIntern = navigateToIntern,
                        )
                    }
                }

                InternListType.SCRAP -> searchScrapsList?.let {
                    items(it.size) { index ->
                        SearchIntern(
                            companyImage = searchScrapsList[index].companyImage,
                            title = searchScrapsList[index].title,
                            announcementId = searchScrapsList[index].announcementId,
                            navigateToIntern = navigateToIntern,
                        )
                    }
                }
            }
        }
    }
}