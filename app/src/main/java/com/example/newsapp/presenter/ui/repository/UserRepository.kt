package com.example.newsapp.presenter.ui.repository

import com.example.newsapp.domain.user.User
import com.example.newsapp.domain.user.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }
    suspend fun isUserLoggedIn(): Boolean {
        val userCount = userDao.getUserCount()
        return userCount > 0
    }
}