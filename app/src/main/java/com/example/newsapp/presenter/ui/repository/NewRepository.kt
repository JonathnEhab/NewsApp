package com.example.newsapp.presenter.ui.repository

import androidx.lifecycle.MutableLiveData
import com.example.newsapp.core.local.NewsDao
import com.example.newsapp.core.remote.api.ApiService
import com.example.newsapp.domain.news.Article
import com.example.newsapp.domain.news.NewsModel
import javax.inject.Inject

class NewRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    val newsData = MutableLiveData<NewsModel>()
    suspend fun getNews(query: String, apiKey: String) : NewsModel{
        val response = apiService.getNews(query, apiKey)
        newsData.postValue(response)
        newsDao.insertAll(response)
        return response
    }

    suspend fun getOfflineNews() {
        val news = newsDao.getAllArticles()
        newsData.postValue(news)

    }
}