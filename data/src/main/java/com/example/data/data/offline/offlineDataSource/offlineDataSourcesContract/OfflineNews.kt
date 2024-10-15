package com.example.data.data.offline.offlineDataSource.offlineDataSourcesContract

import com.example.domain.ApiResult
import com.example.domain.models.LNews

interface OfflineNews {
    // contract fun to return a list of news to domain layer
    suspend fun getAlltNews() : ApiResult<List<LNews>?>
    suspend fun isNewsSaved(id: Int) : Boolean
    suspend fun insertNews(news : LNews) : ApiResult<LNews?>
    suspend fun deleteNews(news : LNews) : ApiResult<LNews?>
}