package com.example.data.data.offline.offlineDataSource.offlineDataSourcesImpl

import com.example.data.data.mappers.toData
import com.example.data.data.offline.MyDao
import com.example.data.data.offline.offlineDataSource.offlineDataSourcesContract.OfflineNews
import com.example.domain.ApiResult
import com.example.domain.models.LNews
import javax.inject.Inject

class OfflineDataSourceImpl  @Inject constructor
    (private val myDao: MyDao) : OfflineNews {

    override suspend fun getAlltNews(): ApiResult<List<LNews>?> {
        return try {
            val response = myDao.getAlltNews()
            val newsList = response.map { it.toLNews() }
            ApiResult.Success(newsList)
        } catch (e: Exception) {
            ApiResult.Failure(throw Exception(e.message ?: "Delete failed"))
        }
    }


    override suspend fun insertNews(news: LNews): ApiResult<LNews?> {
        return try {
            myDao.insertNews(toData(news))
            ApiResult.Success(news)
        } catch (e: Exception) {
            ApiResult.Failure(throw Exception(e.message ?: "Delete failed"))
        }
    }

    override suspend fun deleteNews(news: LNews): ApiResult<LNews?> {
        return try {
            myDao.deleteNews(toData(news))
            ApiResult.Success(news)
        } catch (e: Exception) {
            ApiResult.Failure(throw Exception(e.message ?: "Delete failed"))
        }
    }
}