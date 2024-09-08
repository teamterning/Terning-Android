package com.terning.domain.entity.onboarding

data class SignUpRequest (
    val name : String,
    val profileImage : String,
    val authType : String
)