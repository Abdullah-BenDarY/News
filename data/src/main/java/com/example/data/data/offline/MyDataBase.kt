package com.example.data.data.offline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.data.model.ArticlesItem

@Database(entities = [ArticlesItem::class], version = 1, exportSchema = true)
abstract class MyDataBase() : RoomDatabase() {

    abstract fun articalesDao(): MyDao

    companion object {
        @Volatile
        var db: MyDataBase? = null
    }

}