package com.terning.data.intern.service

import com.terning.data.dto.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface InternService {
    @GET("api/v1/announcements/{internshipAnnouncementId}")
    suspend fun getInternInfo(
        @Path("internshipAnnouncementId") internshipAnnouncementId: Long,
    ): BaseResponse<com.terning.data.intern.dto.response.InternResponseDto>
}