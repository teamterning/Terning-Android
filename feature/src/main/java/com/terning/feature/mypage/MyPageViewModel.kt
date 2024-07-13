package com.terning.feature.mypage

import androidx.lifecycle.ViewModel
import com.terning.domain.repository.MockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(private val repository: MockRepository) : ViewModel() {


}