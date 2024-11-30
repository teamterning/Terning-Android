package com.terning.data.intern.service

import com.terning.core.network.BaseResponse
import com.terning.data.intern.dto.response.InternResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface InternService {
    @GET("api/v1/announcements/{internshipAnnouncementId}")
    suspend fun getInternInfo(
        @Path("internshipAnnouncementId") internshipAnnouncementId: Long,
    ): BaseResponse<InternResponseDto>
}