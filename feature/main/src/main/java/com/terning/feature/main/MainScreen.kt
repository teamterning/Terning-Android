package com.terning.feature.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.terning.core.analytics.EventType
import com.terning.core.designsystem.component.snackbar.TerningBasicSnackBar
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.White
import com.terning.core.designsystem.util.NoRippleInteractionSource
import com.terning.feature.calendar.calendar.navigation.calendarNavGraph
import com.terning.feature.filtering.filteringone.navigation.filteringOneNavGraph
import com.terning.feature.filtering.filteringtwo.navigation.filteringTwoNavGraph
import com.terning.feature.filtering.filteringthree.navigation.filteringThreeNavGraph
import com.terning.feature.filtering.startfiltering.navigation.startFilteringNavGraph
import com.terning.feature.filtering.starthome.navigation.startHomeNavGraph
import com.terning.feature.home.navigation.homeNavGraph
import com.terning.feature.intern.navigation.internNavGraph
import com.terning.feature.intern.navigation.navigateIntern
import com.terning.feature.mypage.mypage.navigation.myPageNavGraph
import com.terning.feature.mypage.profileedit.navigation.profileEditNavGraph
import com.terning.feature.onboarding.signin.navigation.signInNavGraph
import com.terning.feature.onboarding.signup.navigation.signUpNavGraph
import com.terning.feature.onboarding.splash.navigation.splashNavGraph
import com.terning.feature.search.search.navigation.searchNavGraph
import com.terning.feature.search.searchprocess.navigation.searchProcessNavGraph
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    val context = LocalContext.current
    var backPressedState by remember { mutableStateOf(true) }
    var backPressedTime = 0L

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val amplitudeTracker = com.terning.core.analytics.LocalTracker.current

    BackHandler(enabled = backPressedState) {
        if (System.currentTimeMillis() - backPressedTime <= 3000) {
            (context as Activity).finish()
        } else {
            backPressedState = true
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    message = "버튼을 한 번 더 누르면 종료돼요",
                    duration = SnackbarDuration.Short
                )
            }
        }
        backPressedTime = System.currentTimeMillis()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.padding(bottom = 10.dp)
            ) { snackBarData ->
                TerningBasicSnackBar {
                    Text(
                        text = snackBarData.visuals.message,
                        color = White
                    )
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                isVisible = navigator.showBottomBar(),
                tabs = MainTab.entries.toList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigation ->
                    amplitudeTracker.track(
                        type = EventType.CLICK,
                        name = when (navigation) {
                            MainTab.HOME -> "navigation_home"
                            MainTab.CALENDAR -> "navigation_calendar"
                            MainTab.SEARCH -> "navigation_search"
                            MainTab.MY_PAGE -> "navigation_mypage"
                        }
                    )
                    navigator.navigate(navigation)
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                },
                popEnterTransition = {
                    EnterTransition.None
                },
                popExitTransition = {
                    ExitTransition.None
                },
                navController = navigator.navController,
                startDestination = navigator.startDestination
            ) {
                splashNavGraph(navHostController = navigator.navController)
                homeNavGraph(
                    paddingValues = paddingValues,
                    navHostController = navigator.navController,
                    navigateToCalendar = navigator::navigateCalendar,
                    navigateToIntern = navigator::navigateIntern
                )
                calendarNavGraph(
                    paddingValues = paddingValues,
                    navigateIntern = navigator::navigateIntern,
                )
                searchNavGraph(
                    paddingValues = paddingValues,
                    navHostController = navigator.navController
                )
                signInNavGraph(navHostController = navigator.navController)
                signUpNavGraph(navHostController = navigator.navController)
                startFilteringNavGraph(navHostController = navigator.navController)
                startHomeNavGraph(
                    navigateHome = {
                        val navOptions = navOptions {
                            popUpTo(id = navigator.navController.graph.id) {
                                inclusive = true
                            }
                        }
                        navigator.navigateHome(navOptions)
                    }
                )
                filteringOneNavGraph(navHostController = navigator.navController)
                filteringTwoNavGraph(navHostController = navigator.navController)
                filteringThreeNavGraph(navHostController = navigator.navController)
                searchProcessNavGraph(
                    paddingValues = paddingValues,
                    navHostController = navigator.navController
                )
                internNavGraph(
                    navHostController = navigator.navController
                )
                myPageNavGraph(
                    paddingValues = paddingValues,
                    navHostController = navigator.navController
                )
                profileEditNavGraph(navHostController = navigator.navController)
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
        enter = fadeIn() + slideIn { IntOffset(0, 0) },
        exit = fadeOut() + slideOut { IntOffset(0, 0) }
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
