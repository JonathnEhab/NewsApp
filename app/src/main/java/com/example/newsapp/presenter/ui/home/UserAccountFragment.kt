package com.example.newsapp.presenter.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.databinding.FragmentUserAccountBinding
import com.example.newsapp.domain.user.User
import com.example.newsapp.presenter.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserAccountFragment : Fragment() {
    private var _binding: FragmentUserAccountBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserAccountBinding.inflate(inflater, container, false)
        return binding.root   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.getUser()
        lifecycleScope.launch {
                loginViewModel.userStateFlow.collect{user ->
                    user?.let {
                        binding.emailUser.setText(user.email)
                        binding.passwordUser.setText(user.password)
                        binding.passwordUserConfirm.setText(user.password)

                    }
                }
        }
        binding.editBtn.setOnClickListener {
            notEnable(false)
            animateEditText()
            binding.editBtn.visibility=View.GONE
            binding.updateBtn.visibility=View.VISIBLE

        }
        binding.updateBtn.setOnClickListener {
            update()
            notEnable(true)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    private fun notEnable(open :Boolean){
        if (open){
            binding.emailUser.isEnabled=false
            binding.userPassword.isEnabled=false
            binding.userConfirmPassword.visibility=View.GONE
        }else{
            binding.emailUser.isEnabled=true
            binding.userPassword.isEnabled=true
            binding.userConfirmPassword.visibility=View.VISIBLE
        }


    }
     fun animateEditText() {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 1000
        binding.userConfirmPassword.startAnimation(fadeIn)
    }
    private fun update(){
        val email = binding.emailUser.text.toString()
        val password = binding.passwordUser.text.toString()
        val confirmPassword = binding.passwordUserConfirm.text.toString()
        if (email.isNotEmpty()) {
            if (password == confirmPassword) {
                val updatedUser = User(email = email, password = password)
                loginViewModel.updateUserInfo(updatedUser)
                Toast.makeText(requireContext(),"Your info updated successfully",Toast.LENGTH_SHORT ).show()
            }
        }

    }




}