package com.terning.feature.main

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.snackbar.TerningBasicSnackBar
import com.terning.core.designsystem.theme.White
import com.terning.feature.calendar.calendar.navigation.calendarNavGraph
import com.terning.feature.calendar.calendar.navigation.navigateCalendar
import com.terning.feature.filtering.filteringone.navigation.filteringOneNavGraph
import com.terning.feature.filtering.filteringone.navigation.navigateFilteringOne
import com.terning.feature.filtering.filteringthree.navigation.filteringThreeNavGraph
import com.terning.feature.filtering.filteringtwo.navigation.filteringTwoNavGraph
import com.terning.feature.filtering.startfiltering.navigation.navigateStartFiltering
import com.terning.feature.filtering.startfiltering.navigation.startFilteringNavGraph
import com.terning.feature.filtering.starthome.navigation.startHomeNavGraph
import com.terning.feature.home.navigation.homeNavGraph
import com.terning.feature.home.navigation.navigateHome
import com.terning.feature.intern.navigation.internNavGraph
import com.terning.feature.intern.navigation.navigateIntern
import com.terning.feature.main.component.MainBottomBar
import com.terning.feature.mypage.mypage.navigation.myPageNavGraph
import com.terning.feature.mypage.profileedit.navigation.profileEditNavGraph
import com.terning.feature.onboarding.signin.navigation.navigateSignIn
import com.terning.feature.onboarding.signin.navigation.signInNavGraph
import com.terning.feature.onboarding.signup.navigation.navigateSignUp
import com.terning.feature.onboarding.signup.navigation.signUpNavGraph
import com.terning.feature.search.search.navigation.navigateSearch
import com.terning.feature.search.search.navigation.searchNavGraph
import com.terning.feature.search.searchprocess.navigation.navigateSearchProcess
import com.terning.feature.search.searchprocess.navigation.searchProcessNavGraph
import com.terning.feature.splash.navigation.Splash
import com.terning.feature.splash.navigation.splashNavGraph
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen(
    host: String?,
    redirect: String?,
    navigator: MainNavigator = rememberMainNavigator(),
) {
    val context = LocalContext.current
    var backPressedState by remember { mutableStateOf(true) }
    var backPressedTime = 0L

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val amplitudeTracker = LocalTracker.current

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
                tabs = MainTab.entries.toImmutableList(),
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
                startDestination = navigator.getStartDestination(redirect = redirect, host = host)
            ) {
                splashNavGraph(
                    navigateHome = {
                        navigator.navController.navigateHome(
                            navOptions = NavOptions.Builder().setPopUpTo(
                                route = Splash(redirect),
                                inclusive = true
                            ).build()
                        )
                    },
                    navigateSignIn = {
                        navigator.navController.navigateSignIn(
                            navOptions = NavOptions.Builder().setPopUpTo(
                                route = Splash(redirect),
                                inclusive = true
                            ).build()
                        )
                    },
                    navigateCalendar = {
                        navigator.navController.navigateCalendar(
                            navOptions = NavOptions.Builder().setPopUpTo(
                                route = Splash(redirect),
                                inclusive = true
                            ).build()
                        )
                    },
                    navigateSearch = {
                        navigator.navController.navigateSearch(
                            navOptions = NavOptions.Builder().setPopUpTo(
                                route = Splash(redirect),
                                inclusive = true
                            ).build()
                        )
                    }
                )
                homeNavGraph(
                    paddingValues = paddingValues,
                    navigateToCalendar = { navigator.navController.navigateCalendar() },
                    navigateToIntern = { announcementId ->
                        navigator.navController.navigateIntern(announcementId)
                    }
                )
                calendarNavGraph(
                    paddingValues = paddingValues,
                    navigateIntern = { announcementId ->
                        navigator.navController.navigateIntern(announcementId)
                    }
                )
                searchNavGraph(
                    paddingValues = paddingValues,
                    navigateSearchProcess = { navigator.navController.navigateSearchProcess() },
                    navigateIntern = { announcementId ->
                        navigator.navController.navigateIntern(announcementId)
                    }
                )
                signInNavGraph(
                    navigateHome = {
                        val navOptions = navOptions {
                            popUpTo(id = navigator.navController.graph.id) {
                                inclusive = true
                            }
                        }
                        navigator.navController.navigateHome(navOptions)
                    },
                    navigateSignUp = { authId ->
                        val navOptions = navOptions {
                            popUpTo(id = navigator.navController.graph.id) {
                                inclusive = true
                            }
                        }
                        navigator.navController.navigateSignUp(
                            authId = authId,
                            navOptions = navOptions
                        )
                    }
                )
                signUpNavGraph(
                    navigateStartFiltering = { name ->
                        val navOptions = navOptions {
                            popUpTo(id = navigator.navController.graph.id) {
                                inclusive = true
                            }
                        }
                        navigator.navController.navigateStartFiltering(
                            name = name,
                            navOptions = navOptions
                        )
                    }
                )
                startFilteringNavGraph(
                    onStartClick = { name ->
                        navigator.navController.navigateFilteringOne(name)
                    },
                    onLaterClick = {
                        val navOptions = navOptions {
                            popUpTo(id = navigator.navController.graph.id) {
                                inclusive = true
                            }
                        }
                        navigator.navController.navigateHome(navOptions)
                    }
                )
                startHomeNavGraph(
                    navigateHome = {
                        val navOptions = navOptions {
                            popUpTo(id = navigator.navController.graph.id) {
                                inclusive = true
                            }
                        }
                        navigator.navController.navigateHome(navOptions)
                    }
                )
                filteringOneNavGraph(navHostController = navigator.navController)
                filteringTwoNavGraph(navHostController = navigator.navController)
                filteringThreeNavGraph(navHostController = navigator.navController)
                searchProcessNavGraph(
                    paddingValues = paddingValues,
                    navHostController = navigator.navController,
                    navigateIntern = { internshipAnnouncementId ->
                        navigator.navController.navigateIntern(internshipAnnouncementId)
                    }
                )
                internNavGraph(
                    navHostController = navigator.navController
                )
                myPageNavGraph(
                    paddingValues = paddingValues,
                    navHostController = navigator.navController,
                    restartApp = {
                        Intent(context, MainActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(this)
                        }
                    }
                )
                profileEditNavGraph(navHostController = navigator.navController)
            }
        }
    }
}
