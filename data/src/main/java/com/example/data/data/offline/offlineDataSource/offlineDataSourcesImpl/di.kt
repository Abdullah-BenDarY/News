package com.example.data.data.offline.offlineDataSource.offlineDataSourcesImpl

import com.example.data.data.offline.offlineDataSource.offlineDataSourcesContract.OfflineNews
import com.example.data.data.offline.offlineDataSource.offlineDataSourcesImpl.OfflineDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OfflineDataSourceBind(){

    // to inject the news data source interface
    @Binds
    abstract fun bindDataSource (
        dataSourceImpl : OfflineDataSourceImpl
    ): OfflineNews

}