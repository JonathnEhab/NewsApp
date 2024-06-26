package com.example.newsapp.presenter.ui.userAccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.databinding.FragmentSignInBinding
import com.example.newsapp.presenter.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInBtn.setOnClickListener {
            signUp()
        }

    }

    private fun signUp() {
        val email = binding.emailUser.text.toString()
        val password = binding.passwordUser.text.toString()
        val confirmPassword = binding.passwordUserConfirm.text.toString()
        if (email.isNotEmpty()) {
            if (password == confirmPassword) {
                signUpViewModel.signUp(email, password)
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToLoggInFragment())

            } else {
                Toast.makeText(requireContext(), "Password Not Matches", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "Enter confirm email", Toast.LENGTH_LONG).show()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}