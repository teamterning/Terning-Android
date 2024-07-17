package com.terning.domain.repository

import InternshipAnnouncement

interface SearchRepository {
    suspend fun getSearchViewsList(): Result<List<InternshipAnnouncement>>
    suspend fun getSearchScrapsList(): Result<List<InternshipAnnouncement>>
}