package com.nishantsahu.androidblogapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.nishantsahu.androidblogapp.R
import com.nishantsahu.androidblogapp.databinding.FragmentAuthBinding
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment: Fragment(){

    private var binding: FragmentAuthBinding ?= null
    private var navController: NavController ?= null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAuthBinding.inflate(inflater, container, false)

        navController = binding?.let { Navigation.findNavController(it.root.findViewById(R.id.authFragmentNavHost)) }

        binding?.authTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        navController?.navigate(R.id.gotoLoginFragment)
                    }
                    1 -> {
                        navController?.navigate(R.id.gotoSignupFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

