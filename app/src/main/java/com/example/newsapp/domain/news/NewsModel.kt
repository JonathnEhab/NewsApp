package com.example.newsapp.domain.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class NewsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)