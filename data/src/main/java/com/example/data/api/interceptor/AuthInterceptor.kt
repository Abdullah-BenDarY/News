package com.example.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor :Interceptor {

    private val apiKey = "fa507c3940b048d2abd4098a2b72b8c3"
    private val AUTH_HEADER = "apiKey"
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.header(AUTH_HEADER,apiKey)
        return chain.proceed(newBuilder.build())
    }
}