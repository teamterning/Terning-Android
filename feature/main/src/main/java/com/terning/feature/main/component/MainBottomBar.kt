package com.terning.feature.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terning.core.designsystem.extension.customShadow
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.designsystem.util.NoRippleInteractionSource
import com.terning.feature.main.MainTab
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun MainBottomBar(
    isVisible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideIn { IntOffset(0, 0) },
        exit = fadeOut() + slideOut { IntOffset(0, 0) }
    ) {
        NavigationBar(
            containerColor = White,
            modifier = modifier.customShadow(
                shadowWidth = 2.dp,
            ),
        ) {
            tabs.forEach { itemType ->
                NavigationBarItem(
                    interactionSource = NoRippleInteractionSource,
                    selected = currentTab == itemType,
                    onClick = { onTabSelected(itemType) },
                    icon = {
                        Icon(
                            painter = painterResource(id = (itemType.icon)),
                            contentDescription = stringResource(id = itemType.contentDescription)
                        )
                    },
                    label = {
                        Text(
                            stringResource(id = itemType.contentDescription),
                            fontSize = 9.sp
                        )
                    },
                    colors = NavigationBarItemDefaults
                        .colors(
                            selectedIconColor = TerningMain,
                            selectedTextColor = TerningMain,
                            unselectedIconColor = Grey300,
                            unselectedTextColor = Grey300,
                            indicatorColor = White
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBottomBarPreview() {
    TerningPointTheme {
        Column {
            Spacer(Modifier.height(20.dp))
            MainBottomBar(
                isVisible = true,
                tabs = MainTab.entries.toImmutableList(),
                currentTab = MainTab.HOME,
                onTabSelected = {}
            )
        }
    }
}