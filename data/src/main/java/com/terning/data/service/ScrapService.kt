package com.terning.data.service

import com.terning.data.dto.NonDataBaseResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ScrapService {
    @POST("api/v1/scraps/{internshipAnnouncementId}")
    suspend fun postScrap(
        @Path(value = "internshipAnnouncementId") internshipAnnouncementId: Int,
        @Body color: Int,
    ): NonDataBaseResponse
}