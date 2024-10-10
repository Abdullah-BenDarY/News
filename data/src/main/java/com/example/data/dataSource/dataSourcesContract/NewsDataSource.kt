package com.example.data.dataSource.dataSourcesContract

import com.example.domain.ApiResult
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource

// contract between data and domain to pass data from dataLayer to domainLayer
interface NewsDataSource {
    // contract fun to return a list of news to domain layer
    suspend fun getLatestNews(title : String) : ApiResult<List<LNews>?>
    suspend fun getNewsBySource(source : String) : ApiResult<List<LNews>?>
    suspend fun getNewsSource(category : String) : ApiResult<List<ModelNewsSource>?>
    suspend fun getSearchQuery(query : String) : ApiResult<List<LNews>?>
}