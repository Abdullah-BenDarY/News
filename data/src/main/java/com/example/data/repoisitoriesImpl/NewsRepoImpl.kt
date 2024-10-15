package com.example.data.repoisitoriesImpl

import com.example.data.data.offline.offlineDataSource.offlineDataSourcesContract.OfflineNews
import com.example.data.data.online.onlineDataSource.dataSourcesContract.NewsDataSource
import com.example.domain.ApiResult
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(private val newsDataSource: NewsDataSource,
    private val offlineNews: OfflineNews) : NewsRepo {
    override suspend fun getLatestNews(title : String):ApiResult<List<LNews>?> {
      return newsDataSource.getLatestNews(title) }

    override suspend fun getNewsBySource(source: String): ApiResult<List<LNews>?> {
        return newsDataSource.getNewsBySource(source) }

    override suspend fun getNewsSource(category: String): ApiResult<List<ModelNewsSource>?> {
        return newsDataSource.getNewsSource(category) }

    override suspend fun getSearchQuery(query: String): ApiResult<List<LNews>?> {
        return newsDataSource.getSearchQuery(query) }

    override suspend fun getOfflineNews(): ApiResult<List<LNews>?> {
        return offlineNews.getAlltNews() }

    override suspend fun isNewsSaved(id: Int): Boolean = offlineNews.isNewsSaved(id)

    override suspend fun insertNews(news : LNews): ApiResult<LNews?> {
        return offlineNews.insertNews(news) }

    override suspend fun deleteNews(news: LNews): ApiResult<LNews?> {
        return offlineNews.deleteNews(news) }
}