package com.example.newsapp.presenter.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentLoggInBinding
import com.example.newsapp.databinding.FragmentSignInBinding
import com.example.newsapp.presenter.ui.home.HomeActivity
import com.example.newsapp.presenter.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoggInFragment : Fragment() {
    private var _binding: FragmentLoggInBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoggInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.signInBtn.setOnClickListener {
            login()
        }
        binding.createNewAccount.setOnClickListener {
            findNavController().navigate(LoggInFragmentDirections.actionLoggInFragmentToSignInFragment())
        }

    }

    private fun login() {
        val email = binding.emailUser.text.toString()
        val password = binding.passwordUser.text.toString()
        if (email.isEmpty()) {
            binding.emailUser.error = "write your email "
            binding.emailUser.requestFocus()
        } else if (password.isEmpty()) {
            binding.passwordUser.error = "write your password"
            binding.passwordUser.requestFocus()
        } else {
            loginViewModel.login(email, password)


            viewLifecycleOwner.lifecycleScope.launch {
                loginViewModel.loginResult.collect { result ->
                    when (result) {
                        is LoginViewModel.LoginResult.Success -> {
                            startActivity(Intent(requireContext(), HomeActivity::class.java))
                            requireActivity().finish()
                        }
                        is LoginViewModel.LoginResult.InvalidCredentials -> {
                            Toast.makeText(requireContext(), "Invalid Email Or Password", Toast.LENGTH_LONG).show()
                        }
                        else -> {

                        }
                    }
                }
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}