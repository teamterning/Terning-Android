package com.terning.data.search.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.terning.data.search.datasource.SearchDataSource
import com.terning.data.search.dto.request.SearchRequestDto
import com.terning.data.search.dto.response.SearchResultResponseDto

class SearchPagingSource(
    private val query: String,
    private val sortBy: String,
    private val dataSource: SearchDataSource,
) : PagingSource<Int, Pair<Int, SearchResultResponseDto.SearchAnnouncementDto>>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<Int, SearchResultResponseDto.SearchAnnouncementDto>> {
        return try {
            val nextParamKey = params.key ?: 0

            val response = dataSource.getSearch(
                request = SearchRequestDto(
                    keyword = query,
                    sortBy = sortBy,
                    page = nextParamKey,
                    size = params.loadSize
                )
            )
            val totalCount = response.result.totalCount
            val hasNextPage = response.result.hasNext

            val nextKey = if (hasNextPage) nextParamKey + 1 else null

            LoadResult.Page(
                data = response.result.announcements.map {
                    Pair(totalCount, it)
                },
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pair<Int, SearchResultResponseDto.SearchAnnouncementDto>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}