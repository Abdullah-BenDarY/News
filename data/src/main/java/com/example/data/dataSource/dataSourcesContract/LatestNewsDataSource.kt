package com.example.data.dataSource.dataSourcesContract

import com.example.domain.models.LNews

// contract between data and domain to pass data from dataLayer to domainLayer
interface LatestNewsDataSource {
    // contract fun to return a list of news to domain layer
    suspend fun getLatestNews(country : String) : List<LNews>?
}