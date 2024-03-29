package com.example.newsapp.presenter.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.presenter.ui.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    sealed class LoginResult {
        object Success : LoginResult()
        object InvalidCredentials : LoginResult()
    }

    private val _loginResult = MutableStateFlow<LoginResult?>(null)
    val loginResult: StateFlow<LoginResult?> = _loginResult

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

