package com.terning.domain.auth.entity

data class SignUpRequest (
    val name : String,
    val profileImage : String,
    val authType : String,
    val fcmToken: String
)