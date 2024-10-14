package com.example.data.data.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.data.model.ArticlesItem

@Database(entities = [ArticlesItem::class], version = 1, exportSchema = true)
@TypeConverters(SourceTypeConverter::class)
abstract class MyDataBase() : RoomDatabase() {
    abstract fun articalesDao(): MyDao

    companion object {
        @Volatile
        var db: MyDataBase? = null
    }
}