package com.terning.data.datasourceimpl

import com.terning.data.datasource.CalendarDataSource
import com.terning.data.datasourceimpl.CalendarList.getMockScrapList
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.CalendarMonthListRequestDto
import com.terning.data.dto.request.CalendarMonthRequestDto
import com.terning.data.dto.response.CalendarListResponseDto
import com.terning.data.dto.response.CalendarMonthResponseDto
import javax.inject.Inject

class CalendarDataSourceImpl @Inject constructor(

) : CalendarDataSource {
    override suspend fun getScrapMonth(request: CalendarMonthRequestDto): BaseResponse<List<CalendarMonthResponseDto>> =
        BaseResponse(
            status = 200,
            message = "캘린더 > (월간) 스크랩 된 공고 정보 (리스트) 불러오기를 성공했습니다",
            result = getMockScrapList(request.year, request.month)
        )

    override suspend fun getScrapMonthList(request: CalendarMonthListRequestDto): BaseResponse<List<CalendarListResponseDto>> {
        return BaseResponse(
            status = 200,
            message = "캘린더 > (월간) 스크랩 된 공고 정보 (리스트) 불러오기를 성공했습니다",
            result = getMockScrapDetailList(request.year, request.month)
        )
    }


}