package com.terning.feature.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange1
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalYellow
import com.terning.feature.home.home.model.InternFilterData
import com.terning.feature.home.home.model.RecommendInternData
import com.terning.feature.home.home.model.ScrapData
import com.terning.feature.home.home.model.UserNameState
import com.terning.feature.home.home.model.UserScrapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _userName by mutableStateOf<UserNameState>(
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

    private val _scrapState = MutableStateFlow<UserScrapState>(
        UserScrapState(
            isScrapExist = true,
            scrapData = getScrapData()
        )
    )
    val scrapData get() = _scrapState.asStateFlow()

    private val _recommendInternState = MutableStateFlow<List<RecommendInternData>>(
        getRecommendData()
    )
    val recommendInternData get() = _recommendInternState.asStateFlow()

    fun setGrade(grade: Int) {
        userName.internFilter?.grade = grade
    }

    fun setWorkingPeriod(workingPeriod: Int) {
        userName.internFilter?.workingPeriod = workingPeriod
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

private fun getRecommendData(): List<RecommendInternData> = listOf(
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 22,
        workingPeriod = 2,
        isScrapped = true,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "ㅇㄻㅇㅁㄻㄹㅇㅁㅇㄹ",
        dDay = 9,
        workingPeriod = 6,
        isScrapped = false,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = false,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = false,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = false,
    ),
    RecommendInternData(
        imgUrl = "https://reqres.in/img/faces/7-image.jpg",
        title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
        dDay = 2,
        workingPeriod = 4,
        isScrapped = true,
    ),
)