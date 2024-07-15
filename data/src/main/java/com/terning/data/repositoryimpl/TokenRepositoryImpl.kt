package com.terning.data.repositoryimpl

import com.terning.data.local.TerningDataStore
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val terningDataStore: TerningDataStore
) {
}