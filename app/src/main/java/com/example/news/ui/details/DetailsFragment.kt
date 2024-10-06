package com.example.news.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.base.BaseFragment
import com.example.news.base.BaseViewModel
import com.example.news.databinding.FragmentDetailsBinding
import com.example.news.ui.home.HomeViewModel


class DetailsFragment : BaseFragment<FragmentDetailsBinding, BaseViewModel>() {

    private val _viewModel: HomeViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun initViewModel(): BaseViewModel {
        return _viewModel
    }



}