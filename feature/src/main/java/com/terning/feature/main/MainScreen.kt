package com.terning.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import com.terning.core.designsystem.component.topappbar.LogoTopAppBar
import com.terning.core.designsystem.component.topappbar.MyPageTopAppBar
import com.terning.core.designsystem.component.topappbar.TerningBasicTopAppBar
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.White
import com.terning.core.navigation.MainTabRoute
import com.terning.core.util.NoRippleInteractionSource
import com.terning.feature.calendar.navigation.calendarNavGraph
import com.terning.feature.home.navigation.homeNavGraph
import com.terning.feature.mypage.navigation.myPageNavGraph
import com.terning.feature.onboarding.signin.navigation.signInNavGraph
import com.terning.feature.onboarding.signup.navigation.signUpNavGraph
import com.terning.feature.search.navigation.searchNavGraph
import com.terning.feature.searchprocess.navigation.searchProcessNavGraph

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        topBar = {
            when (navigator.currentTab) {
                MainTab.HOME -> LogoTopAppBar()
                MainTab.CALENDAR -> TerningBasicTopAppBar()
                MainTab.SEARCH -> LogoTopAppBar()
                MainTab.MY_PAGE -> MyPageTopAppBar()
                else -> TerningBasicTopAppBar()
            }
        },
        bottomBar = {
            MainBottomBar(
                isVisible = navigator.showBottomBar(),
                tabs = MainTab.entries.toList(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NavHost(
                navController = navigator.navController,
                startDestination = navigator.startDestination
            ) {
                homeNavGraph()
                calendarNavGraph()
                searchNavGraph(navHostController = navigator.navController)
                myPageNavGraph()
                signInNavGraph(navHostController = navigator.navController)
                signUpNavGraph()
                searchProcessNavGraph()
            }
        }
    }
}


@Composable
private fun MainTopBar(
    isVisible: Boolean,
    tabs: List<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
    ) {
    }
}

@Composable
private fun MainBottomBar(
    isVisible: Boolean,
    tabs: List<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
    ) {
        NavigationBar(containerColor = White) {
            tabs.forEach { itemType ->
                NavigationBarItem(
                    interactionSource = NoRippleInteractionSource,
                    selected = currentTab == itemType,
                    onClick = {
                        onTabSelected(itemType)
                    },
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
                    colors = androidx.compose.material3.NavigationBarItemDefaults
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
