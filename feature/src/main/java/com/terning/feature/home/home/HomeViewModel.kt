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
            internFilter = listOf(4, 1, 2024, 8)
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

}


private fun getScrapData(): List<ScrapData>
= listOf(
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