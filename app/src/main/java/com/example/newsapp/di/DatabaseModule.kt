package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.core.local.NewsDao
import com.example.newsapp.domain.user.UserDao
import com.example.newsapp.domain.user.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun getRoomInstance (@ApplicationContext context: Context) : UserDatabase {
        return Room.databaseBuilder(context
            , UserDatabase::class.java
            , "Users")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getMyDao (userDatabase: UserDatabase) : UserDao {
        return userDatabase.userDao()
    }


}