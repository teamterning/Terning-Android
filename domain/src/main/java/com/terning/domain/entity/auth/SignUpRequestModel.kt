package com.terning.domain.entity.auth

data class SignUpRequestModel (
    val name : String,
    val profileImage : Int,
    val authType : String
)