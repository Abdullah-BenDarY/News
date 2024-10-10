package com.example.data.dataSource.dataSourcesImpl

import com.example.data.api.WebServices
import com.example.data.dataSource.dataSourcesContract.NewsDataSource
import com.example.domain.ApiResult
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import executeApi
import javax.inject.Inject

// contract implementation for news data source
class NewsDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : NewsDataSource {

    //TODO (pass the data from api to domain)
    override suspend fun getLatestNews(title: String): ApiResult<List<LNews>?> {
        val response = apiService.getLatestNews(title = title)
        return executeApi {
            response.articles?.map {
                it?.toLNews() ?: LNews()
            }
        }
    }

    override suspend fun getNewsBySource(source: String): ApiResult<List<LNews>?> {
        val response = apiService.getNewsBySource(source = source)
        return executeApi {
            response.articles?.map {
                it?.toLNews() ?: LNews()
            }
        }
    }

    override suspend fun getNewsSource(category: String): ApiResult<List<ModelNewsSource>?> {
        val response = apiService.getNewsSources(category)
        return executeApi {
            response.sources?.map {
                it?.toNewsSource() ?: ModelNewsSource()
            }
        }
    }

    override suspend fun getSearchQuery(query: String): ApiResult<List<LNews>?> {
        val response = apiService.getSearchQuery(query)
        return executeApi {
            response.articles?.map {
                it?.toLNews() ?: LNews()
            }
        }
    }
}