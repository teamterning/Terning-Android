package com.terning.feature.intern

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.customShadow
import com.terning.core.extension.toast
import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternInfoModel
import com.terning.feature.R
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
) {
    val internState by viewModel.internUiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

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
                internUiState = internState,
                internInfoModel = (internState.loadState as UiState.Success).data
            )
        }
    }
}

@Composable
fun InternScreen(
    modifier: Modifier = Modifier,
    viewModel: InternViewModel = hiltViewModel(),
    internUiState: InternUiState,
    internInfoModel: InternInfoModel,
) {
    val decimal = DecimalFormat("#,###")

    val internInfoList = listOf(
        stringResource(id = R.string.intern_info_d_day) to internInfoModel.deadline,
        stringResource(id = R.string.intern_info_working) to internInfoModel.workingPeriod,
        stringResource(id = R.string.intern_info_start_date) to internInfoModel.startDate,
    )

    val qualificationList = listOf(
        stringResource(id = R.string.intern_recruitment_target) to internInfoModel.qualification,
        stringResource(id = R.string.intern_info_work) to internInfoModel.jobType,
    )

    if (internUiState.showWeb) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    loadUrl(internInfoModel.url)
                }
            },
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(
                title = stringResource(id = R.string.intern_top_app_bar_title),
                modifier = Modifier.customShadow(
                    color = Grey200,
                    offsetY = 2.dp
                ),
                onBackButtonClick = {
                },
            )
        },
        bottomBar = {
            InternBottomBar(
                modifier = modifier,
                scrapCount = decimal.format(internInfoModel.scrapCount),
                scrapId = internInfoModel.scrapId,
                onScrapClick = {
                    viewModel.updateScrapDialogVisible(true)
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
        ) {
            item {
                Column(
                    modifier = modifier.padding(
                        top = 24.dp,
                        start = 24.dp,
                        end = 24.dp
                    )
                ) {
                    Spacer(modifier = modifier.padding(top = 16.dp))

                    InternCompanyInfo(
                        modifier = modifier,
                        companyImage = internInfoModel.companyImage,
                        company = internInfoModel.company,
                        companyCategory = internInfoModel.companyCategory
                    )

                    Spacer(modifier = modifier.padding(top = 20.dp))

                    InternTitle(
                        modifier = modifier,
                        dDay = internInfoModel.dDay,
                        title = internInfoModel.title,
                        viewCount = decimal.format(internInfoModel.viewCount)
                    )

                    Spacer(modifier = modifier.padding(top = 16.dp))

                    InternPageTitle(
                        modifier = modifier,
                        text = stringResource(id = R.string.intern_sub_title_intern_summary)
                    )

                    Column(
                        modifier = modifier.padding(
                            top = 4.dp,
                            bottom = 4.dp,
                            start = 10.dp
                        )
                    ) {
                        internInfoList.forEach { (title, value) ->
                            InternInfoRow(title, value)
                        }
                    }

                    Spacer(modifier = modifier.padding(top = 16.dp))

                    InternPageTitle(
                        modifier = modifier,
                        text = stringResource(id = R.string.intern_info_request)
                    )

                    Column(
                        modifier = modifier.padding(
                            top = 4.dp,
                            bottom = 4.dp,
                            start = 10.dp
                        )
                    ) {
                        qualificationList.forEach { (title, value) ->
                            InternInfoRow(title, value)
                        }
                    }

                    Spacer(modifier = modifier.padding(top = 16.dp))

                    InternPageTitle(
                        modifier = modifier,
                        text = stringResource(id = R.string.intern_sub_title_intern_detail)
                    )

                    Column(
                        modifier = modifier.padding(
                            start = 10.dp,
                            top = 5.dp,
                            bottom = 20.dp
                        )
                    ) {
                        SelectionContainer {
                            Text(
                                text = internInfoModel.detail.trimIndent(),
                                style = TerningTheme.typography.body3,
                                color = Grey400
                            )
                        }
                    }
                }
            }
        }

        if (internUiState.isScrapDialogVisible) {
            TerningBasicDialog(
                onDismissRequest = {
                    viewModel.updateScrapDialogVisible(false)
                },
                content = {},
            )
        }
    }
}