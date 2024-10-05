package com.example.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor :Interceptor {

    private val API_KEY = "1432d9e1909f421886d7e2c874da9da8"
    private val AUTH_HEADER = "authorization"
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.header(AUTH_HEADER,API_KEY)
        return chain.proceed(newBuilder.build())
    }
}