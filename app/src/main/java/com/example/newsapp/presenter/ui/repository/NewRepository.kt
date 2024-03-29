package com.example.newsapp.presenter.ui.repository

// NewRepository.kt
import com.example.newsapp.core.local.NewsDao
import com.example.newsapp.core.remote.api.ApiService
import com.example.newsapp.domain.news.NewsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NewRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    private val _newsData = MutableStateFlow<NewsModel?>(null)
    val newsData: Flow<NewsModel?> = _newsData

    suspend fun getNews(query: String, apiKey: String): NewsModel {
        val response = apiService.getNews(query, apiKey)
        _newsData.value = response
        newsDao.insertAll(response)
        return response
    }

    suspend fun getOfflineNews(): NewsModel? {
        return newsDao.getAllArticles()
    }
}

