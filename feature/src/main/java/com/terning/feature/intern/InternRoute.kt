package com.terning.feature.intern

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R

@Composable
fun InternRoute(
    navController: NavHostController,
) {
    InternScreen(
        navController = navController
    )
}

@Composable
fun InternScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(
                title = "공고 정보",
                modifier = Modifier,
                onBackButtonClick = {},
                listOf(
                    {},
                    {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_intern_share_22),
                                contentDescription = stringResource(id = R.string.intern_share_icon),
                                modifier = modifier
                                    .noRippleClickable { }
                            )
                        }
                    },
                    { Spacer(modifier = modifier.padding(end = 8.dp)) }
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
        ) {
            Text(text = "공고 정보")
        }
    }
}