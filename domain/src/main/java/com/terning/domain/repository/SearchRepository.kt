package com.terning.domain.repository

import InternshipAnnouncementModel

interface SearchRepository {
    suspend fun getSearchViewsList(): Result<List<InternshipAnnouncementModel>>
    suspend fun getSearchScrapsList(): Result<List<InternshipAnnouncementModel>>
}