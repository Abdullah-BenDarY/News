package com.example.data.data.offline

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.data.model.ArticlesItem

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(lNews: ArticlesItem)

    @Delete
    suspend fun deleteNews(lNews: ArticlesItem)

    @Query("select * from ArticlesItem")
   suspend fun getAlltNews (): List<ArticlesItem>

    @Query("SELECT COUNT(*) FROM ArticlesItem WHERE id = :id")
    suspend fun countNewsById(id: Int): Int


}