package com.terning.feature.main

import com.terning.domain.entity.response.MockResponseModel


sealed class MockState {
    data object Empty : MockState()
    data object Loading : MockState()
    data class Success(val mockList: List<MockResponseModel>) : MockState()
}