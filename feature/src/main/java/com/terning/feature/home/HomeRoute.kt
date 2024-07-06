package com.terning.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {

        Box(
            modifier = Modifier
                .background(TerningMain)
                .padding(vertical = 12.dp)
                .width(148.dp)
                .height(36.dp)
        )

        Text(
            text = stringResource(
                id = R.string.home_today_title_start
            )
                    + "남지우"
                    + stringResource(
                id = R.string.home_today_title_end
            ),
            modifier = Modifier
                .padding(top = 11.dp),
            style = TerningTheme.typography.title1,
            color = Black,
        )
    }
}
