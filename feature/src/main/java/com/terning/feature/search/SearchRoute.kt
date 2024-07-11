package com.terning.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.textfield.SearchTextField
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey100
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.search.component.ImageSlider
import com.terning.feature.search.component.InternListType
import com.terning.feature.search.component.SearchInternList
import com.terning.feature.searchprocess.navigation.navigateSearchProcess

@Composable
fun SearchRoute(
    navController: NavHostController,
) {
    SearchScreen(
        navController = navController
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val images = listOf(
        R.drawable.ic_nav_search,
        R.drawable.ic_check,
        R.drawable.ic_nav_my_page,
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            LogoTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp,
                        vertical = 16.dp
                    )
                    .clickable {
                        navController.navigateSearchProcess()
                    }
            ) {
                SearchTextField(
                    textStyle = TerningTheme.typography.detail2,
                    hint = stringResource(R.string.search_text_field_hint),
                    leftIcon = R.drawable.ic_nav_search,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    readOnly = true,
                )
            }

            ImageSlider(
                images = images
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = stringResource(id = R.string.search_today_popular),
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp),
                style = TerningTheme.typography.title1,
                color = Black
            )

            SearchInternList(type = InternListType.VIEW)
            HorizontalDivider(
                thickness = 4.dp,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Grey100,
            )
            SearchInternList(type = InternListType.SCRAP)
        }
    }

}
