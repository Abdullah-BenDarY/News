package com.example.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.domain.models.LNews
import com.example.news.adapters.AdapterLatestNews
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.utils.COUNTRY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private val adapterLatestNews = AdapterLatestNews()
    private val _viewModel : HomeViewModel by viewModels ()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?
    )
            = FragmentHomeBinding.inflate(inflater,container,false)

    override fun initViewModel(): HomeViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.getLatestNews(COUNTRY)
        observe()
    }

    private fun observe() {
        _viewModel.lNews.observe(viewLifecycleOwner){latestNews ->
            latestNews?.let {
                initViews(it)
            }
        }
    }

    private fun initViews(lNews: List<LNews>) {
        adapterLatestNews.lNewsList = lNews
        binding.rvLatestNews.adapter = adapterLatestNews
        adapterLatestNews.notifyDataSetChanged()
        adapterLatestNews.onItemClickListener?.onItemClick(lNews[0],0)

    }


}