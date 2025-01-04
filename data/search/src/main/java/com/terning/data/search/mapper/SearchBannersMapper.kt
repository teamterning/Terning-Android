package com.terning.data.search.mapper

import com.terning.data.search.dto.response.SearchBannersResponseDto
import com.terning.domain.search.entity.SearchBanner

fun SearchBannersResponseDto.toSearchBannerList(): List<SearchBanner> {
    return banners.map {
        SearchBanner(
            imageUrl = it.imageUrl,
            url = it.link,
        )
    }
}