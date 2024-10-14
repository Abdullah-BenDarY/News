package com.example.data.data.offline

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.data.model.ArticlesItem

@Dao
interface MyDao {

    @Insert
    suspend fun insertNews(lNews: ArticlesItem)

    @Delete
    suspend fun deleteNews(lNews: ArticlesItem)

    @Query("select * from ArticlesItem")
   suspend fun getAlltNews (): List<ArticlesItem>
}