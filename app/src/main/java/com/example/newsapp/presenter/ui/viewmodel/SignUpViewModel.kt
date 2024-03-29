package com.example.newsapp.presenter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.user.User
import com.example.newsapp.presenter.ui.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun signUp(username: String, password: String) {
        viewModelScope.launch {

            val user = User(username = username, password = password)
            userRepository.insert(user)
        }
    }

}