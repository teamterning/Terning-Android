package com.terning.data.dto.response

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
    @SerialName("startYearMonth")
    val startYearMonth: String,
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
    @SerialName("isScrapped")
    val isScrapped: Boolean,
    @SerialName("color")
    val color: String? = null,
)