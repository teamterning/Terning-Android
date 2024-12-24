package com.terning.data.home.datasourceimpl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.terning.data.home.dto.response.HomeRecommendInternResponseDto
import com.terning.data.home.service.HomeService

// TODO: HomeDataSource의 getRecommendIntern()를 대체하는 PagingSource 구현체
class HomePagingSourceImpl(
    private val service: HomeService,
    private val sortBy: String,
    private val startYear: Int,
    private val  startMonth: Int,
) : PagingSource<Int, HomeRecommendInternResponseDto.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeRecommendInternResponseDto.Result> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = service.getRecommendIntern(
                sortBy = sortBy,
                startYear = startYear,
                startMonth = startMonth,
                // TODO: nextPageNumber = nextPageNumber,
            )
            return LoadResult.Page(
                data = response.result.result,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HomeRecommendInternResponseDto.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}