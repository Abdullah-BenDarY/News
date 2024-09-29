package com.example.data.repoisitoriesImpl

import com.example.data.dataSource.dataSourcesContract.LatestNewsDataSource
import com.example.domain.models.LNews
import com.example.domain.repoisitories.LatestNewsRepo
import javax.inject.Inject

class LatestNewsRepoImpl @Inject constructor(private val latestNewsDataSource: LatestNewsDataSource) : LatestNewsRepo {
    override suspend fun getLatestNews(country : String): List<LNews>? {
      return latestNewsDataSource.getLatestNews(country)
    }
}