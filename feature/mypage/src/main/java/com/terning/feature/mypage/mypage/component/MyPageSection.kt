package com.terning.feature.mypage.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.mypage.mypage.model.MyPageUiModel
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun MyPageSection(
    items: ImmutableList<MyPageUiModel>,
    modifier: Modifier = Modifier
) {
    Column( // TODO : 수정하기
        modifier = modifier
            .padding(
                top = 8.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 20.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 20.dp,
                    start = 16.dp,
                    end = 9.dp
                )
        ) {
            items.forEach { item ->
                when (item) {
                    is MyPageUiModel.Header -> {
                        Text(
                            text = stringResource(id = item.text),
                            style = TerningTheme.typography.body6,
                            color = Grey400,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    is MyPageUiModel.MyPageItem -> {
                        MyPageItem(
                            text = stringResource(id = item.text),
                            icon = item.leadingIcon,
                            onButtonClick = item.onItemClick,
                            trailingContent = item.trailingContent
                        )
                    }

                    is MyPageUiModel.HorizontalDivider -> {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 20.dp),
                            thickness = 1.dp,
                            color = Grey150
                        )
                    }
                }
            }
        }
    }
}