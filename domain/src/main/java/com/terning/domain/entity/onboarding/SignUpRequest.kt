package com.terning.domain.entity.onboarding

data class SignUpRequest (
    val name : String,
    val profileImage : Int,
    val authType : String
)