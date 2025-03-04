package com.terning.intern

import com.terning.core.designsystem.state.UiState
import com.terning.domain.intern.entity.InternInfo
import com.terning.domain.intern.repository.InternRepository
import com.terning.feature.intern.InternViewModel
import com.watcha.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class InternViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: InternViewModel
    private val internRepository: InternRepository = mock()

    @Before
    fun setUp() {
        viewModel = InternViewModel(internRepository)
    }

    @Test
    fun `getInternInfo 성공 시 UiState_Success 설정`() = runTest {
        val mockInternInfo = InternInfo(
            dDay = "10",
            title = "테스트 제목",
            deadline = "테스트 내용",
            workingPeriod = "3",
            startYearMonth = "2023.01",
            scrapCount = 10,
            viewCount = 10,
            company = "테스트 제목",
            companyCategory = "테스트 내용",
            companyImage = "테스트 내용",
            qualification = "테스트 내용",
            jobType = "테스트 내용",
            detail = "테스트 내용",
            url = "테스트 내용",
            isScrapped = true
        )

        doReturn(Result.success(mockInternInfo))
            .`when`(internRepository).getInternInfo(1)

        viewModel.getInternInfo(1)
        advanceUntilIdle()

        val uiState = viewModel.internUiState.first()
        assert(uiState.loadState is UiState.Success)
        assert((uiState.loadState as UiState.Success).data == mockInternInfo)
    }

}