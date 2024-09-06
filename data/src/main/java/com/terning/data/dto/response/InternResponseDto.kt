package com.terning.data.dto.response

import com.terning.domain.entity.intern.InternInfoModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InternResponseDto(
    @SerialName("dDay")
    val dDay: String,
    @SerialName("title")
    val title: String,
    @SerialName("deadline")
    val deadline: String,
    @SerialName("workingPeriod")
    val workingPeriod: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("scrapCount")
    val scrapCount: Int,
    @SerialName("viewCount")
    val viewCount: Int,
    @SerialName("company")
    val company: String,
    @SerialName("companyCategory")
    val companyCategory: String,
    @SerialName("companyImage")
    val companyImage: String,
    @SerialName("qualification")
    val qualification: String,
    @SerialName("jobType")
    val jobType: String,
    @SerialName("detail")
    val detail: String,
    @SerialName("url")
    val url: String,
    @SerialName("scrapId")
    val scrapId: Long? = null,
) {
    fun toInternEntity(): InternInfoModel {
        return InternInfoModel(
            dDay = dDay,
            title = title,
            deadline = deadline,
            workingPeriod = workingPeriod,
            startDate = startDate,
            scrapCount = scrapCount,
            viewCount = viewCount,
            company = company,
            companyCategory = companyCategory,
            companyImage = companyImage,
            qualification = qualification,
            jobType = jobType,
            detail = detail,
            url = url,
            scrapId = scrapId
        )
    }
}
