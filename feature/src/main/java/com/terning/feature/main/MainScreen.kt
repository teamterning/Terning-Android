package com.terning.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.White
import com.terning.core.util.NoRippleInteractionSource
import com.terning.feature.calendar.calendar.navigation.calendarNavGraph
import com.terning.feature.filtering.filtering.navigation.filteringOneNavGraph
import com.terning.feature.filtering.filtering.navigation.filteringThreeNavGraph
import com.terning.feature.filtering.filtering.navigation.filteringTwoNavGraph
import com.terning.feature.filtering.startfiltering.navigation.startFilteringNavGraph
import com.terning.feature.filtering.starthome.navigation.startHomeNavGraph
import com.terning.feature.home.changefilter.navigation.changeFilterNavGraph
import com.terning.feature.home.home.navigation.homeNavGraph
import com.terning.feature.intern.navigation.internNavGraph
import com.terning.feature.mypage.navigation.myPageNavGraph
import com.terning.feature.onboarding.signin.navigation.signInNavGraph
import com.terning.feature.onboarding.signup.navigation.signUpNavGraph
import com.terning.feature.onboarding.splash.navigation.splashNavGraph
import com.terning.feature.search.search.navigation.searchNavGraph
import com.terning.feature.search.searchprocess.navigation.searchProcessNavGraph

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
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
                splashNavGraph(navHostController = navigator.navController)
                homeNavGraph(navHostController = navigator.navController)
                calendarNavGraph(navHostController = navigator.navController)
                searchNavGraph(navHostController = navigator.navController)
                myPageNavGraph(navHostController = navigator.navController)
                signInNavGraph(navHostController = navigator.navController)
                signUpNavGraph(navHostController = navigator.navController)
                filteringOneNavGraph(navHostController = navigator.navController)
                filteringTwoNavGraph(navHostController = navigator.navController)
                filteringThreeNavGraph(navHostController = navigator.navController)
                searchProcessNavGraph(navHostController = navigator.navController)
                changeFilterNavGraph(navHostController = navigator.navController)
                startFilteringNavGraph(navHostController = navigator.navController)
                startHomeNavGraph(navHostController = navigator.navController)
                internNavGraph()
            }
        }
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
        enter = expandVertically(expandFrom = Alignment.Top) { 20 },
        exit = shrinkVertically(animationSpec = tween()) { fullHeight ->
            fullHeight / 2
        },
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
