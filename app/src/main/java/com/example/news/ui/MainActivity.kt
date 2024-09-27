package com.example.news.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.news.R
import com.example.news.base.BaseActivity
import com.example.news.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linkNavHostWithBottomNavigation()
    }

    private fun linkNavHostWithBottomNavigation() {
        val navController = findNavController(R.id.fragmentContainerView)
        binding.btmNav.setupWithNavController(navController)
    }

    override fun inflateBinding(): ActivityMainBinding
    = ActivityMainBinding.inflate(layoutInflater)

}