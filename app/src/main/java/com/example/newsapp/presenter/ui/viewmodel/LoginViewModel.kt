package com.example.newsapp.presenter.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.user.User
import com.example.newsapp.presenter.ui.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _loginResult = MutableStateFlow<LoginResult?>(null)
    val loginResult: StateFlow<LoginResult?> = _loginResult


    private val _userStateFlow = MutableStateFlow<User?>(null)
    val userStateFlow: StateFlow<User?> = _userStateFlow

    fun getUser() {
        viewModelScope.launch {
            _userStateFlow.value = userRepository.getUser()
        }
    }

    fun updateUserInfo(user: User) {
        viewModelScope.launch {
            userRepository.updateUserInfo(user)
            _userStateFlow.value = user
        }
    }

    sealed class LoginResult {
        object Success : LoginResult()
        object InvalidCredentials : LoginResult()
    }



    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.login(email, password)
            if (user != null) {
                _loginResult.value = LoginResult.Success
            } else {
                _loginResult.value = LoginResult.InvalidCredentials
            }
        }
    }

    suspend fun isUserLoggedIn(): Boolean {
        return userRepository.isUserLoggedIn()
    }
}

