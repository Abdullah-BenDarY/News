package com.example.data.api

import com.example.data.api.model.ModelLatestNews
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("top-headlines")
    suspend fun getLatestNews(
//        @Query ("apiKey") category :String? = "fa507c3940b048d2abd4098a2b72b8c3",
        @Query ("country") country : String
    ): ModelLatestNews
}