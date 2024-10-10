package com.example.domain.repoisitories

import com.example.domain.ApiResult
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource

// contract to receive data from data layer and pass it to domain layer
interface NewsRepo {
    suspend fun getLatestNews(title : String): ApiResult<List<LNews>?>

    suspend fun getNewsBySource(source : String): ApiResult<List<LNews>?>

    suspend fun getNewsSource(category : String): ApiResult<List<ModelNewsSource>?>

    suspend fun getSearchQuery(query : String): ApiResult<List<LNews>?>
}