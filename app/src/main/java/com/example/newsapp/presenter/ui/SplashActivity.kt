package com.example.newsapp.presenter.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.databinding.ActivitySplashBinding
import com.example.newsapp.presenter.ui.home.HomeActivity
import com.example.newsapp.presenter.ui.userAccount.RegisterActivity
import com.example.newsapp.presenter.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            animateSplashText()
            delay(1000)
            if (loginViewModel.isUserLoggedIn()) {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, RegisterActivity::class.java))
            }
            finish()
        }
    }
     suspend fun animateSplashText() {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000
        binding.splash.startAnimation(fadeIn)
    }
}
