package com.terning.data.datasourceimpl

import com.terning.data.datasource.InternDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.InternResponseDto
import com.terning.data.service.InternService
import javax.inject.Inject

class InternDataSourceImpl @Inject constructor(
    private val internService: InternService,
) : InternDataSource {
    override suspend fun getInternInfo(id: Int): BaseResponse<InternResponseDto> {
        return BaseResponse(
            status = 200,
            message = "Success",
            result = InternResponseDto(
                dDay = "디데이",
                title = "타이틀",
                deadline = "데드라인",
                workingPeriod = "근무기간",
                startDate = "시작날",
                scrapCount = 2332,
                viewCount = 3423,
                company = "회사이름",
                companyCategory = "회사카테고리",
                companyImage = "https://media.nudge-community.com/35176",
                qualification = "졸업 예정~~~~~~,~~휴학생 가능",
                jobType = "그래픽 디자인, UX/UI/GUI 디자인!#,!kflsmf",
                detail = "모니모니의 마케팅 팀은 소비자에게 삶의 솔루션으로서...",
                url = "https://intern.announcement.url",
                isScrapped = true
            )
        )
    }
}
