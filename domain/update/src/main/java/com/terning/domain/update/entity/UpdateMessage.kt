package com.terning.domain.update.entity

data class UpdateMessage(
    val version: String,
    val majorUpdateTitle: String,
    val majorUpdateBody: String,
    val patchUpdateTitle: String,
    val patchUpdateBody: String,
)
