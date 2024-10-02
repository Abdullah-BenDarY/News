package com.example.data.api

import com.example.data.api.model.ModelLatestNews
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("top-headlines")
    suspend fun getLatestNews(
        @Query ("country") country : String
    ): ModelLatestNews

    @GET("top-headlines")
    suspend fun getNewsByCategory(
        @Query ("category") category : String
    ): ModelLatestNews
}