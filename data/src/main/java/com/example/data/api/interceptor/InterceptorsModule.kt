package com.example.data.api.interceptor

import android.content.Context
import com.example.data.AppNetworkHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import java.io.File
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorsModule {

    @Provides
    @Singleton
    @OkHttpAuthInterceptor
    fun provideAuthInterceptor():Interceptor{
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    @OkHttpCacheInterceptor
    fun provideCacheInterceptor():Interceptor{
        return CacheInterceptor()
    }
    @Provides
    @Singleton
    @OkHttpOfflineCacheInterceptor
    fun provideOfflineCacheInterceptor(networkHandler: AppNetworkHandler):Interceptor{
        return OfflineCacheInterceptor(networkHandler)
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(
            File(context.cacheDir, "news-cache"),
            10L * 1024L * 1024L) // 10 Mb cache size

    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpAuthInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpCacheInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpOfflineCacheInterceptor
