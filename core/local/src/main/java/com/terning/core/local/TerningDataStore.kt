package com.terning.core.local

interface TerningDataStore {
    var accessToken: String
    var refreshToken: String
    var userId: Long
    var alarmAvailable: Boolean
    fun clearInfo()
}