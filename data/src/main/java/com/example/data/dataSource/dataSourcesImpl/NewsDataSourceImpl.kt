package com.example.data.dataSource.dataSourcesImpl

import com.example.data.api.WebServices
import com.example.data.dataSource.dataSourcesContract.NewsDataSource
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import javax.inject.Inject

// contract implementation for news data source
class NewsDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : NewsDataSource {

    //TODO (pass the data from api to domain)
    override suspend fun getLatestNews(title: String): List<LNews>? {
        val response = apiService.getLatestNews(title = title)
        return response.articles?.map {
            it?.toLNews() ?: LNews()
        }
    }

    override suspend fun getNewsSource(category: String): List<ModelNewsSource>? {
        val response = apiService.getNewsSources(category)
        return response.sources?.map {
            it?.toNewsSource() ?: ModelNewsSource()
        }
    }
}