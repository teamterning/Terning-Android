package com.terning.feature.intern

import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.extension.customShadow
import com.terning.core.designsystem.extension.toast
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.domain.intern.entity.InternInfo
import com.terning.feature.dialog.cancel.ScrapCancelDialog
import com.terning.feature.dialog.detail.ScrapDialog
import com.terning.feature.intern.component.InternBottomBar
import com.terning.feature.intern.component.InternCompanyInfo
import com.terning.feature.intern.component.InternInfoRow
import com.terning.feature.intern.component.InternPageTitle
import com.terning.feature.intern.component.InternTitle
import com.terning.feature.intern.model.InternUiState
import java.text.DecimalFormat

@Composable
fun InternRoute(
    announcementId: Long = 0,
    viewModel: InternViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val internState by viewModel.internUiState.collectAsStateWithLifecycle(lifecycleOwner)
    val amplitudeTracker = LocalTracker.current

    LaunchedEffect(key1 = true) {
        viewModel.getInternInfo(announcementId)
    }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is InternViewSideEffect.Toast -> context.toast(sideEffect.message)
                }
            }
    }

    when (internState.loadState) {
        UiState.Loading -> {}
        UiState.Empty -> {}
        is UiState.Failure -> {}
        is UiState.Success -> {
            InternScreen(
                announcementId = announcementId,
                internUiState = internState,
                internInfo = (internState.loadState as UiState.Success).data,
                navController = navController,
                onDismissCancelDialog = {
                    with(viewModel) {
                        updateScrapCancelDialogVisibility(false)
                        getInternInfo(announcementId)
                    }
                },
                onDismissScrapDialog = {
                    with(viewModel) {
                        updateInternDialogVisibility(false)
                        getInternInfo(announcementId)
                    }
                },
                onClickCancelButton = {
                    viewModel.updateScrapCancelDialogVisibility(true)
                },
                onClickScrapButton = {
                    amplitudeTracker.track(
                        type = EventType.CLICK,
                        name = "detail_scrap"
                    )
                    viewModel.updateInternDialogVisibility(true)
                }
            )
        }
    }
}

@Composable
fun InternScreen(
    announcementId: Long,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    internUiState: InternUiState,
    internInfo: InternInfo,
    onDismissCancelDialog: (Boolean) -> Unit,
    onDismissScrapDialog: (Boolean) -> Unit,
    onClickCancelButton: (InternInfo) -> Unit,
    onClickScrapButton: (InternInfo) -> Unit,
) {
    val context = LocalContext.current

    val decimal = DecimalFormat("#,###")

    val internInfoList = listOf(
        stringResource(id = R.string.intern_info_d_day) to internInfo.deadline,
        stringResource(id = R.string.intern_info_working) to internInfo.workingPeriod,
        stringResource(id = R.string.intern_info_start_date) to internInfo.startYearMonth,
    )

    val qualificationList = listOf(
        stringResource(id = R.string.intern_recruitment_target) to internInfo.qualification,
        stringResource(id = R.string.intern_info_work) to internInfo.jobType,
    )

    LaunchedEffect(internUiState.showWeb) {
        if (internUiState.showWeb) {
            CustomTabsIntent.Builder().build().launchUrl(context, internInfo.url.toUri())
        }
    }

    Column(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxHeight()
            .background(White),
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            BackButtonTopAppBar(
                title = stringResource(id = R.string.intern_top_app_bar_title),
                modifier = Modifier.customShadow(
                    color = Grey200,
                    offsetY = 2.dp
                ),
                onBackButtonClick = {
                    navController.popBackStack()
                },
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Column(
                        modifier = Modifier.padding(
                            top = 24.dp,
                            start = 24.dp,
                            end = 24.dp
                        )
                    ) {
                        Spacer(modifier = Modifier.padding(top = 16.dp))

                        InternCompanyInfo(
                            modifier = Modifier,
                            companyImage = internInfo.companyImage,
                            company = internInfo.company,
                            companyCategory = internInfo.companyCategory
                        )

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        InternTitle(
                            modifier = Modifier,
                            dDay = internInfo.dDay,
                            title = internInfo.title,
                            viewCount = decimal.format(internInfo.viewCount)
                        )

                        Spacer(modifier = Modifier.padding(top = 16.dp))

                        InternPageTitle(
                            modifier = Modifier,
                            text = stringResource(id = R.string.intern_sub_title_intern_summary)
                        )

                        Column(
                            modifier = Modifier.padding(
                                top = 4.dp,
                                bottom = 4.dp,
                                start = 10.dp
                            )
                        ) {
                            internInfoList.forEach { (title, value) ->
                                InternInfoRow(title, value)
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 13.dp))

                        InternPageTitle(
                            modifier = Modifier,
                            text = stringResource(id = R.string.intern_info_request)
                        )

                        Column(
                            modifier = Modifier.padding(
                                top = 4.dp,
                                bottom = 4.dp,
                                start = 10.dp
                            )
                        ) {
                            qualificationList.forEach { (title, value) ->
                                InternInfoRow(title, value)
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 13.dp))

                        InternPageTitle(
                            modifier = Modifier,
                            text = stringResource(id = R.string.intern_sub_title_intern_detail)
                        )

                        Column(
                            modifier = Modifier.padding(
                                start = 10.dp,
                                top = 5.dp,
                                bottom = 20.dp
                            )
                        ) {
                            SelectionContainer {
                                Text(
                                    text = internInfo.detail.trimIndent(),
                                    style = TerningTheme.typography.body3,
                                    color = Grey400
                                )
                            }
                        }
                    }
                }
            }
        }

        InternBottomBar(
            modifier = Modifier,
            internInfo = internInfo,
            onScrapClick = {
                if (internInfo.isScrapped) {
                    onClickCancelButton(internInfo)
                } else {
                    onClickScrapButton(internInfo)
                }
            },
        )

        if (internUiState.scrapCancelDialogVisibility) {
            ScrapCancelDialog(
                internshipAnnouncementId = announcementId,
                onDismissRequest = onDismissCancelDialog
            )
        }

        if (internUiState.internDialogVisibility) {
            ScrapDialog(
                title = internInfo.title,
                scrapColor = CalRed,
                deadline = internInfo.deadline,
                startYearMonth = internInfo.startYearMonth,
                workingPeriod = internInfo.workingPeriod,
                internshipAnnouncementId = announcementId,
                companyImage = internInfo.companyImage,
                isScrapped = internInfo.isScrapped,
                onDismissRequest = onDismissScrapDialog,
                onClickChangeColor = { },
                onClickNavigateButton = { }
            )
        }
    }
}