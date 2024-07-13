package com.terning.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.terning.core.designsystem.component.topappbar.MyPageTopAppBar
import com.terning.core.designsystem.theme.Back
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.feature.R
import com.terning.feature.mypage.component.MyPageItem

@Composable
fun MyPageRoute(
    myPageViewModel: MyPageViewModel = hiltViewModel(),
) {

    MyPageScreen()
}

@Composable
fun MyPageScreen(
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            MyPageTopAppBar(modifier = Modifier)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()).background(TerningMain)
        ) {
            Text(
                text = stringResource(id = R.string.my_page_name, "남지우"),
                modifier = Modifier.padding(
                    top = 21.dp,
                    start = 24.dp,
                ),
                style = TerningTheme.typography.heading1,
            )
            Spacer(modifier = Modifier.weight(2f))
            MyPageInfo(
                modifier = Modifier
                    .customShadow(
                        color = Grey200,
                        shadowRadius = 5.dp,
                        shadowWidth = 2.dp
                    )
                    .background(color = Black)
            )
            Text(text = "hi", )
        }
    }
}

@Composable
fun MyPageInfo(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 20.dp)
            .customShadow(
                color = Grey200,
                shadowRadius = 5.dp,
                shadowWidth = 2.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(

        ) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                MyPageItem(
                    text = stringResource(id = R.string.my_page_notice),
                    modifier = modifier.padding(bottom = 6.dp)
                )
                MyPageItem(
                    text = stringResource(id = R.string.my_page_send),
                    modifier = modifier.padding(bottom = 6.dp)
                )
                MyPageItem(text = stringResource(id = R.string.my_page_version))
            }
        }
    }
}
