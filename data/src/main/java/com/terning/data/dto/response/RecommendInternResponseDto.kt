package com.terning.data.dto.response

import com.terning.domain.entity.response.RecommendInternModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendInternResponseDto(
    @SerialName("internshipAnnouncementId")
    val internshipAnnouncementId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("dDay")
    val dDay: String,
    @SerialName("workingPeriod")
    val workingPeriod: String,
    @SerialName("companyImage")
    val companyImage: String,
    @SerialName("isScrapped")
    val isScrapped: Boolean,
) {
//    fun toRecommendInternEntity(): List<RecommendInternResponseModel> = listOf(
//        RecommendInternResponseModel(
//            internshipAnnouncementId = this.internshipAnnouncementId,
//            title = this.title,
//            dDay = this.dDay,
//            workingPeriod = this.workingPeriod,
//            companyImage = this.companyImage,
//            isScrapped = this.isScrapped,
//        )
//    )

    fun toRecommendInternEntity(): List<RecommendInternModel> =
        listOf(
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = true,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "ㅇㄻㅇㅁㄻㄹㅇㅁㅇㄹ",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = false,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = true,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = false,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = true,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = true,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = false,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = true,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = false,
            ),
            RecommendInternModel(
                internshipAnnouncementId = 1234123132,
                title = "[유한킴벌리] 그린캠프 w.대학생 숲 활동가",
                dDay = "D-2",
                workingPeriod = "4개월",
                companyImage = "https://reqres.in/img/faces/7-image.jpg",
                isScrapped = true,
            ),
        )
}
