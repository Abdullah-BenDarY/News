package com.example.data.repoisitoriesImpl

import com.example.data.dataSource.dataSourcesContract.NewsDataSource
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(private val newsDataSource: NewsDataSource) : NewsRepo {
    override suspend fun getLatestNews(title : String): List<LNews>? {
      return newsDataSource.getLatestNews(title)
    }
    override suspend fun getNewsSource(category: String): List<ModelNewsSource>? {
        return newsDataSource.getNewsSource(category)
    }
}