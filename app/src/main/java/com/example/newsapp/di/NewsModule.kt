package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.core.local.NewsDao
import com.example.newsapp.core.local.NewsDatabase
import com.example.newsapp.core.remote.api.ApiService
import com.example.newsapp.domain.user.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Singleton
    @Provides
    fun getNewsInstance (@ApplicationContext context: Context) : NewsDatabase {
        return Room.databaseBuilder(context
            , NewsDatabase::class.java
            , "news_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun newDao(newsDatabase: NewsDatabase) : NewsDao {
        return newsDatabase.newsDao()
    }
}