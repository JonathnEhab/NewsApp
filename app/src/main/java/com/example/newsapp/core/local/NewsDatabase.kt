package com.example.newsapp.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.domain.news.Article
import com.example.newsapp.domain.news.NewsModel
import com.example.newsapp.domain.news.Source

@Database(entities = [NewsModel::class], version = 2, exportSchema = false)
@TypeConverters(NewsConverter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}