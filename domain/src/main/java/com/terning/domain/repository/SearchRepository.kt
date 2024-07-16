package com.terning.domain.repository

import com.terning.domain.entity.response.InternshipAnnouncement

interface SearchRepository {
    suspend fun getSearchViewsList(): Result<List<InternshipAnnouncement>>
}