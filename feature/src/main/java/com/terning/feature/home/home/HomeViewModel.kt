package com.terning.feature.home.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.terning.feature.home.home.model.UserNameState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _userName by mutableStateOf<UserNameState>(
        UserNameState(
            userName = "남지우자랑스러운티엘이되",
            internFilter = listOf(1, 2, 2024, 7)
        )
    )
    val userName get() = _userName

}