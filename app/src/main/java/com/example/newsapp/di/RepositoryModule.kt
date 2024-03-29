package com.example.newsapp.di

import com.example.newsapp.core.local.NewsDao
import com.example.newsapp.core.remote.api.ApiService
import com.example.newsapp.presenter.ui.repository.NewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideNewsRepository(apiService: ApiService, newsDao: NewsDao): NewRepository {
        return NewRepository(apiService, newsDao)
    }

}