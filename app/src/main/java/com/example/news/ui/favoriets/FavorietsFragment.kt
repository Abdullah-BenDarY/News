package com.example.news.ui.favoriets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.base.BaseViewModel
import com.example.news.databinding.FragmentFavorietsBinding
import com.example.news.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavorietsFragment : BaseFragment<FragmentFavorietsBinding, BaseViewModel>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )
    = FragmentFavorietsBinding.inflate(inflater,container,false)

    private val _viewModel :BaseViewModel by viewModels ()

    override fun initViewModel(): BaseViewModel {
        return _viewModel
    }

}