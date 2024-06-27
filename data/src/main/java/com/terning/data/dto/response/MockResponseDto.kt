package com.terning.data.dto.response

import com.terning.domain.entity.response.MockResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MockResponseDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<MockData>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class MockData(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val first_name: String,
        @SerialName("last_name")
        val last_name: String,
        @SerialName("avatar")
        val avatar: String
    )

    @Serializable
    data class Support(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )

    fun toMockEntity(): List<MockResponseModel> = data.map {
        MockResponseModel(
            avatar = it.avatar,
            email = it.email,
            firstName = it.first_name,
            lastName = it.last_name
        )
    }

}