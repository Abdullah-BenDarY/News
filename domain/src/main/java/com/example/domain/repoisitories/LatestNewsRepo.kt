package com.example.domain.repoisitories

import com.example.domain.models.LNews

// contract to receive data from data layer and pass it to domain layer
interface LatestNewsRepo {
    suspend fun getLatestNews(country : String): List<LNews>?
}