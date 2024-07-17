package com.terning.domain.entity.request

data class SignUpRequestModel (
    val name : String,
    val profileImage : Int,
    val authType : String
)