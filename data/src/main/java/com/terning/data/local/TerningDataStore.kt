package com.terning.data.local

interface TerningDataStore {
    var accessToken: String
    var refreshToken: String
    var userId: Long
    fun clearInfo()
}