package com.example.data.dataSource.dataSourcesContract

import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource

// contract between data and domain to pass data from dataLayer to domainLayer
interface NewsDataSource {
    // contract fun to return a list of news to domain layer
    suspend fun getLatestNews(title : String) : List<LNews>?
    suspend fun getNewsSource(category : String) : List<ModelNewsSource>?
}