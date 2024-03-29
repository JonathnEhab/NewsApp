package com.example.newsapp.presenter.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.news.Article
import com.example.newsapp.domain.news.NewsModel
import com.example.newsapp.presenter.ui.repository.NewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewRepository
) : ViewModel()  {
    private val EXMPLE ="example"
    private val API_KEY ="344e477ac241420db1e7501341b3ff48"
 var articleNews:LiveData<NewsModel> = repository.newsData
    fun articleNews(){
            viewModelScope.launch {
                try {
                    val response =  repository.getNews(EXMPLE,API_KEY)
                    repository.newsData.value = response
                    Log.d("TAG view model", "articleNews:${response.articles.size} ")
                }catch (e :Exception){
                     repository.getOfflineNews()
                }
            }
    }
}