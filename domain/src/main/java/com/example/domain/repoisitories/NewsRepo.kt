package com.example.domain.repoisitories

import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource

// contract to receive data from data layer and pass it to domain layer
interface NewsRepo {
    suspend fun getLatestNews(title : String): List<LNews>?

    suspend fun getNewsSource(category : String): List<ModelNewsSource>?
}