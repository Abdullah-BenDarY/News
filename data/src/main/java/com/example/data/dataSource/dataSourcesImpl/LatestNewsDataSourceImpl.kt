package com.example.data.dataSource.dataSourcesImpl

import com.example.data.api.WebServices
import com.example.data.dataSource.dataSourcesContract.LatestNewsDataSource
import com.example.domain.models.LNews
import javax.inject.Inject

// contract implementation for news data source
class LatestNewsDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : LatestNewsDataSource {

    //TODO (pass the data from api to domain)
    override suspend fun getLatestNews(country: String): List<LNews>? {
        val response = apiService.getLatestNews(country)
        return response.articles?.map {
            it?.toLNews() ?: LNews()
        }
    }
}