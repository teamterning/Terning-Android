package com.terning.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NonDataBaseResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
)
