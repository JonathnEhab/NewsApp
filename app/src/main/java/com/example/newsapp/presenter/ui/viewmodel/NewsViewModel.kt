package com.example.newsapp.presenter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.news.NewsModel
import com.example.newsapp.presenter.ui.repository.NewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewRepository
) : ViewModel() {

    private val EXAMPLE = "example"
    private val API_KEY = "344e477ac241420db1e7501341b3ff48"

    private val _articleNews = MutableStateFlow<NewsModel?>(null)
    val articleNews: StateFlow<NewsModel?> = _articleNews

    fun fetchArticleNews() {
        viewModelScope.launch {
            try {
                val response = repository.getNews(EXAMPLE, API_KEY)
                _articleNews.value = response
            } catch (e: Exception) {
                repository.getOfflineNews()?.let {
                    _articleNews.value = it
                }
            }
        }
    }
}

