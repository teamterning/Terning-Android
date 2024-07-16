package com.terning.data.dto.request

import com.terning.domain.entity.request.ScrapRequestModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScrapRequestDto(
    @SerialName("internshipAnnouncementId")
    val internshipAnnouncementId: Int,
    @SerialName("color")
    val color: Int,
)

fun ScrapRequestModel.toScrapRequestDto(): ScrapRequestDto =
    ScrapRequestDto(internshipAnnouncementId, color)