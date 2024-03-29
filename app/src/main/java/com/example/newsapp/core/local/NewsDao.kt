package com.example.newsapp.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.domain.news.Article
import com.example.newsapp.domain.news.NewsModel
import com.example.newsapp.domain.news.Source

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: NewsModel)



    @Query("SELECT * FROM articles")
    suspend fun getAllArticles():NewsModel




}