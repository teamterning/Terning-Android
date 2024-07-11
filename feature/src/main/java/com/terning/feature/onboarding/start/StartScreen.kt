package com.terning.feature.onboarding.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.onboarding.filtering.navigation.navigateFiltering

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.start_title),
            style = TerningTheme.typography.title1,
            modifier = modifier
                .padding(bottom = 35.dp),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.img_start),
            contentDescription = stringResource(id = R.string.start_main_image),
        )
        Spacer(modifier = modifier.weight(1f))
        RectangleButton(
            style = TerningTheme.typography.button0,
            paddingVertical = 20.dp,
            text = R.string.start_button,
            onButtonClick = { navController.navigateFiltering() },
            modifier = modifier.padding(bottom = 12.dp)
        )
    }
}