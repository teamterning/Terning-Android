package com.terning.domain.entity.auth

data class SignUpRequest (
    val name : String,
    val profileImage : Int,
    val authType : String
)