package com.nishantsahu.androidblogapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nishantsahu.androidblogapp.AuthViewModel
import com.nishantsahu.androidblogapp.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment:Fragment() {

    private var binding: FragmentSettingsBinding ?= null
    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({lifecycle}){ user->
            binding?.apply {
                usernameEditText.setText(user?.username ?:"")
                emailEditText.setText(user?.email ?:"")
                imageEditText.setText(user?.image.toString() ?:"")
                bioEditText.setText(user?.bio ?:"")
            }
        }
        binding?.saveBtn?.setOnClickListener{
            authViewModel.updateUser(
                    usernameEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordEditText.text.toString(),
                    bioEditText.text.toString(),
                    imageEditText.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}