package com.terning.feature.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange1
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.state.UiState
import com.terning.domain.entity.response.RecommendInternModel
import com.terning.domain.repository.InternRepository
import com.terning.feature.home.home.model.InternFilterData
import com.terning.feature.home.home.model.ScrapData
import com.terning.feature.home.home.model.UserNameState
import com.terning.feature.home.home.model.UserScrapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val internRepository: InternRepository
) : ViewModel() {
    private val _userName by mutableStateOf(
        UserNameState(
            userName = "남지우자랑스러운티엘이되",
            internFilter = InternFilterData(
                grade = 1,
                workingPeriod = 1,
                startYear = 2024,
                startMonth = 7,
            )
        )
    )
    val userName get() = _userName

    private val _scrapState = MutableStateFlow(
        UserScrapState(
            isScrapExist = true,
            scrapData = getScrapData()
        )
    )
    val scrapData get() = _scrapState.asStateFlow()

    private val _recommendInternState =
        MutableStateFlow<UiState<List<RecommendInternModel>>>(UiState.Empty)
    val recommendInternState get() = _recommendInternState.asStateFlow()

    fun setGrade(grade: Int) {
        userName.internFilter?.grade = grade
    }

    fun setWorkingPeriod(workingPeriod: Int) {
        userName.internFilter?.workingPeriod = workingPeriod
    }

    init {
        getRecommendInternsData("deadlineSoon")
    }


    fun getRecommendInternsData(sortBy: String) {
        _recommendInternState.value = UiState.Loading
        viewModelScope.launch {
            internRepository.getRecommendIntern(sortBy).onSuccess { internList ->
                _recommendInternState.value = UiState.Success(internList)
            }.onFailure { exception: Throwable ->
                _recommendInternState.value = UiState.Failure(exception.message ?: " ")
            }
        }
    }
}

private fun getScrapData(): List<ScrapData> = listOf(
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        scrapColor = CalBlue1,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 숲 활동가",
        scrapColor = CalPink,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        scrapColor = CalYellow,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 숲 활동가",
        scrapColor = CalBlue2,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        scrapColor = CalGreen1,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 숲 활동가",
        scrapColor = CalOrange1,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        scrapColor = CalGreen2,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 숲 활동가",
        scrapColor = CalPink,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        scrapColor = CalBlue1,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 숲 활동가",
        scrapColor = CalPink,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        scrapColor = CalBlue1,
    ),
    ScrapData(
        internTitle = "[유한킴벌리] 그린캠프 숲 활동가",
        scrapColor = CalPink,
    ),
)
