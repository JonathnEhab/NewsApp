package com.example.newsapp.core.remote.api

import com.example.newsapp.domain.news.Article
import com.example.newsapp.domain.news.NewsModel
import com.example.newsapp.domain.news.Source
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("everything")
    suspend fun getNews(@Query("q") query: String, @Query("apiKey") apiKey: String): NewsModel

    @GET("everything")
    suspend fun getNewsDetails(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Path("url") url: String
    ): Article


}