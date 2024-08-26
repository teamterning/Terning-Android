package com.terning.feature.intern

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.component.dialog.TerningBasicDialog
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.customShadow
import com.terning.core.extension.toast
import com.terning.feature.R
import com.terning.feature.intern.component.InternBottomBar
import com.terning.feature.intern.component.InternCompanyInfo
import com.terning.feature.intern.component.InternInfoRow
import com.terning.feature.intern.component.InternPageTitle
import com.terning.feature.intern.model.InternState
import java.text.DecimalFormat

@Composable
fun InternRoute(
    announcementId: Long = 0,
    viewModel: InternViewModel = hiltViewModel(),
) {
    val internState by viewModel.state.collectAsStateWithLifecycle()

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

    InternScreen(
        internState = internState
    )
}

@Composable
fun InternScreen(
    modifier: Modifier = Modifier,
    viewModel: InternViewModel = hiltViewModel(),
    internState: InternState,
) {
    val decimal = DecimalFormat("#,###")

    val internInfoList = listOf(
        stringResource(id = R.string.intern_info_d_day) to internState.deadline,
        stringResource(id = R.string.intern_info_working) to internState.workingPeriod,
        stringResource(id = R.string.intern_info_start_date) to internState.startDate,
    )

    val qualificationList = internState.qualification.split(",").map { it.trim() }
    val jobTypeList = internState.jobType.split(",").map { it.trim() }

    if (internState.showWeb) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    loadUrl(internState.url)
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
                scrapCount = decimal.format(internState.scrapCount),
                scrapId = internState.scrapId,
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
                    Row(
                        modifier = modifier
                            .border(
                                width = 1.dp,
                                color = TerningMain,
                                shape = RoundedCornerShape(size = 12.dp)
                            ),
                        horizontalArrangement = Arrangement.spacedBy(
                            0.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = internState.dDay,
                            style = TerningTheme.typography.title5,
                            color = TerningMain,
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 2.dp
                            )
                        )
                    }

                    Text(
                        text = internState.title,
                        style = TerningTheme.typography.title2,
                        color = Black,
                        modifier = modifier.padding(
                            top = 4.dp,
                            bottom = 16.dp
                        )
                    )

                    Column(
                        modifier = modifier
                            .border(
                                width = 1.dp,
                                color = TerningMain,
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            6.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        internInfoList.forEach { (title, value) ->
                            InternInfoRow(title, value)
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.End),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                top = 9.dp,
                            )
                    ) {
                        Text(
                            text = stringResource(id = R.string.intern_view_count),
                            style = TerningTheme.typography.detail3,
                            color = Grey400
                        )
                        Text(
                            text = "${decimal.format(internState.viewCount)}회",
                            style = TerningTheme.typography.button4,
                            color = Grey400,
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.Top,
                ) {
                    InternPageTitle(
                        modifier = modifier,
                        text = stringResource(id = R.string.intern_sub_title_intern_info)
                    )
                    InternCompanyInfo(
                        modifier = modifier,
                        companyImage = internState.companyImage,
                        company = internState.company,
                        companyCategory = internState.companyCategory
                    )
                    InternPageTitle(
                        modifier = modifier,
                        text = stringResource(id = R.string.intern_sub_title_intern_summary)
                    )
                    Column(
                        modifier = modifier
                            .padding(
                                start = 24.dp,
                                bottom = 16.dp,
                                end = 24.dp
                            )
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                3.dp,
                                Alignment.Start
                            ),
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                modifier = modifier
                                    .weight(2f),
                                verticalAlignment = Alignment.Top,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_first_info_20),
                                    contentDescription = null
                                )
                                Text(
                                    text = stringResource(id = R.string.intern_info_request),
                                    style = TerningTheme.typography.button2,
                                    color = Black
                                )
                            }

                            Column(
                                modifier = modifier
                                    .weight(5f)
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            ) {
                                qualificationList.forEach { qualification ->
                                    Text(
                                        text = qualification,
                                        style = TerningTheme.typography.body4,
                                        color = Grey400,
                                    )
                                }
                            }
                        }
                    }
                    Column(
                        modifier = modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                3.dp,
                                Alignment.Start
                            ),
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                modifier = modifier
                                    .weight(2f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_second_info_20),
                                    contentDescription = null,
                                )
                                Text(
                                    text = stringResource(id = R.string.intern_info_work),
                                    style = TerningTheme.typography.button2,
                                    color = Black
                                )
                            }

                            Column(
                                modifier = modifier
                                    .weight(5f)
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                            ) {
                                jobTypeList.forEach { jobType ->
                                    Text(
                                        text = jobType,
                                        style = TerningTheme.typography.body4,
                                        color = Grey400,
                                    )
                                }
                            }
                        }
                    }
                    InternPageTitle(
                        modifier = modifier,
                        text = stringResource(id = R.string.intern_sub_title_intern_detail)
                    )
                    Column(
                        modifier = modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 20.dp
                        )
                    ) {
                        Text(
                            text = internState.detail.trimIndent(),
                            style = TerningTheme.typography.detail1,
                            color = Grey400
                        )
                    }
                }
            }
        }
        if (internState.isScrapDialogVisible) {
            TerningBasicDialog(
                onDismissRequest = {
                    viewModel.updateScrapDialogVisible(false)
                },
                content = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InternScreenPreview() {
    TerningPointTheme {
        InternScreen(
            internState = InternState(),
        )
    }
}