package com.example.news

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.news.base.BaseFragment
import com.example.news.base.BaseViewModel
import com.example.news.databinding.FragmentSplashBinding
import com.example.news.utils.hideBottomNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding , BaseViewModel>() {

    private val _viewModel : BaseViewModel by viewModels ()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentSplashBinding.inflate(inflater,container,false)

    override fun initViewModel(): BaseViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delayingIntent()
    }

    override fun onStart() {
        super.onStart()
        hideBottomNav()
    }

    private fun delayingIntent() =
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }, 2000)


}