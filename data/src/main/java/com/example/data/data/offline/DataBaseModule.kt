package com.example.data.data.offline

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun createDatabase(@ApplicationContext context: Context): MyDataBase {
        return Room.databaseBuilder(
            context,
            MyDataBase::class.java, "db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}