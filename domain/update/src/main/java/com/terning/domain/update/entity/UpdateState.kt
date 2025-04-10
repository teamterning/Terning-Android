package com.terning.domain.update.entity

sealed class UpdateState {
    data object InitialState : UpdateState()
    data object NoUpdateAvailable : UpdateState()
    data class MajorUpdateAvailable(val title: String, val content: String) : UpdateState()
    data class PatchUpdateAvailable(val title: String, val content: String) : UpdateState()
}