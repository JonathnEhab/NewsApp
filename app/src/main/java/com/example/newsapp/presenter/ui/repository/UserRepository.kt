package com.example.newsapp.presenter.ui.repository

import com.example.newsapp.domain.user.User
import com.example.newsapp.domain.user.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    suspend fun getUser(): User? {
        return userDao.getUser()
    }

    suspend fun updateUserInfo(user: User) {
        userDao.updateUserInfo(user)
    }


}