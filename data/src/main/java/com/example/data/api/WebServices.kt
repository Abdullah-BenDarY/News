package com.example.data.api

import com.example.data.api.model.ModelLatestNews
import com.example.data.api.model.ModelNewsSource
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("top-headlines")
    suspend fun getLatestNews(
        @Query ("category") title : String
    ): ModelLatestNews

    @GET("top-headlines/sources")
    suspend fun getNewsSources(
        @Query("category") source: String
    ) : ModelNewsSource

    @GET("top-headlines")
    suspend fun getNewsBySource(
        @Query ("sources") source : String
    ): ModelLatestNews
}