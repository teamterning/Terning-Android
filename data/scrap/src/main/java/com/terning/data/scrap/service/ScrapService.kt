package com.terning.data.scrap.service

import com.terning.data.dto.NonDataBaseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ScrapService {
    @POST("api/v1/scraps/{internshipAnnouncementId}")
    suspend fun postScrap(
        @Path(value = "internshipAnnouncementId") internshipAnnouncementId: Long,
        @Body body: com.terning.data.scrap.dto.request.ScrapColorRequestDto,
    ): NonDataBaseResponse

    @DELETE("api/v1/scraps/{scrapId}")
    suspend fun deleteScrap(
        @Path(value = "scrapId") scrapId: Long,
    ): NonDataBaseResponse

    @PATCH("api/v1/scraps/{scrapId}")
    suspend fun patchScrap(
        @Path(value = "scrapId") scrapId: Long,
        @Body body: com.terning.data.scrap.dto.request.ScrapColorRequestDto,
    ): NonDataBaseResponse
}