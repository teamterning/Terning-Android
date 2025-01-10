package com.terning.data.search.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchBannersResponseDto(
    @SerialName("banners")
    val banners: List<BannerDto>,
) {
    @Serializable
    data class BannerDto(
        @SerialName("imageUrl")
        val imageUrl: String,
        @SerialName("link")
        val link: String,
    )
}