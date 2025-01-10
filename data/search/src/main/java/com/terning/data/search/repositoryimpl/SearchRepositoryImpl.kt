package com.terning.data.search.repositoryimpl

import com.terning.data.search.datasource.SearchDataSource
import com.terning.data.search.dto.request.SearchRequestDto
import com.terning.data.search.mapper.toSearchBannerList
import com.terning.data.search.mapper.toSearchPopularAnnouncementList
import com.terning.data.search.mapper.toSearchResultList
import com.terning.domain.search.entity.SearchBanner
import com.terning.domain.search.entity.SearchPopularAnnouncement
import com.terning.domain.search.entity.SearchResult
import com.terning.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {
    override suspend fun getSearchList(
        query: String,
        sortBy: String,
        page: Int,
        size: Int,
    ): Result<List<SearchResult>> {
        return runCatching {
            searchDataSource.getSearch(
                SearchRequestDto(
                    keyword = query,
                    sortBy = sortBy,
                    page = page,
                    size = size
                )
            ).result.toSearchResultList()
        }
    }

    override suspend fun getSearchViewsList(): Result<List<SearchPopularAnnouncement>> =
        runCatching {
            searchDataSource.getSearchViews().result.toSearchPopularAnnouncementList()
        }

    override suspend fun getSearchScrapsList(): Result<List<SearchPopularAnnouncement>> =
        runCatching {
            searchDataSource.getSearchScraps().result.toSearchPopularAnnouncementList()
        }

    override suspend fun getSearchBannersList(): Result<List<SearchBanner>> =
        kotlin.runCatching {
            searchDataSource.getSearchBanners().result.toSearchBannerList()
        }
}