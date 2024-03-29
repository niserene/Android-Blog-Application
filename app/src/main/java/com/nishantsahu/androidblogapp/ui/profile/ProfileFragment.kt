package com.nishantsahu.androidblogapp.ui.profile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.nishantsahu.androidblogapp.R
import com.nishantsahu.androidblogapp.databinding.FragmentProfileBinding

class ProfileFragment:Fragment() {

    private var binding: FragmentProfileBinding ?=null
    lateinit var profileViewModel: ProfileViewModel
    private var username:String? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        arguments?.let {
            username = it.getString(resources.getString(R.string.arg_username))
            username?.let { profileViewModel.getProfile(it) }
            Toast.makeText(context, "Loading article..", Toast.LENGTH_LONG).show()
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        profileViewModel.profile.observe({lifecycle}){
            binding?.apply {
                usernameTextView.text = it.username
                fullNameTextView.text = it.username
                bioTextView.text = it.bio
            }
//            Glide.with(view.context).load(uri).into(binding!!.profileImageView) TODO
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}