package com.example.newsapp.core.local

import androidx.room.TypeConverter
import com.example.newsapp.domain.news.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromFilmList(films: List<Article>?): String? {
        return films?.let {
            gson.toJson(films
            )}
    }

    @TypeConverter
    fun toFilmList(article: String?): List<Article>? {
        val type = object: TypeToken<List<Article>>() {}.type
        return gson.fromJson<List<Article>>(article, type)
    }
}