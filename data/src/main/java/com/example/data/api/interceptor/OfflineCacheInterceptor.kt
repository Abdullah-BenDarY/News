package com.example.data.api.interceptor

import android.util.Log
import com.example.data.AppNetworkHandler
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
class OfflineCacheInterceptor @Inject constructor(private val networkHandler: AppNetworkHandler) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days

        if (!networkHandler.isNetworkAvailable()) {
            // Offline, only use cache
            request
                .removeHeader("Pragma")
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
        } else {
            // Online, fetch fresh data
            request
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=0") // fetch fresh data
        }

        // Proceed with the request and get the response
        val response = chain.proceed(request.build())

        // If we're offline and there's no cached response, throw an exception
        if (!networkHandler.isNetworkAvailable() && response.cacheResponse == null) {
            throw IOException("No cached data available and no internet connection")
        }

        return response
    }
}