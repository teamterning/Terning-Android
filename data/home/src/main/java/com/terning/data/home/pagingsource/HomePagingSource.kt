package com.terning.data.home.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.terning.data.home.datasource.HomeDataSource
import com.terning.data.home.dto.response.HomeRecommendInternResponseDto

class HomePagingSource(
    private val sortBy: String,
    private val dataSource: HomeDataSource
) : PagingSource<Int, Pair<Int, HomeRecommendInternResponseDto.Result>>() {

    private var hasNextPage = false

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<Int, HomeRecommendInternResponseDto.Result>> {
        return try {
            val nextParamKey = params.key ?: 0

            val response = dataSource.getRecommendIntern(sortBy = sortBy, page = nextParamKey)
            val totalCount = response.result.totalCount
            hasNextPage = response.result.hasNextPage

            LoadResult.Page(
                data = response.result.result.map {
                    Pair(totalCount, it)
                },
                prevKey = null, // 다음 페이지 로딩만 가능하도록 설정
                nextKey = if (hasNextPage) nextParamKey + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pair<Int, HomeRecommendInternResponseDto.Result>>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}