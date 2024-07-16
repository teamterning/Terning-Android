package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.InternResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface InternService {
    @GET("api/v1/announcements/{internshipAnnouncementId}")
    suspend fun getInternInfo(
        @Path("internshipAnnouncementId") internshipAnnouncementId: Int,
    ): BaseResponse<InternResponseDto>
}