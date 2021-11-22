package com.nishantsahu.androidblogapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.nishantsahu.androidblogapp.databinding.FragmentLoginBinding

class LoginFragment:Fragment() {

    private var binding: FragmentLoginBinding ?= null
    val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            loginBtn.setOnClickListener{
                authViewModel.login(emailEditText.text.toString(), passwordEditText.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}