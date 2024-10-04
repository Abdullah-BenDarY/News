package com.example.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.domain.models.LNews
import com.example.news.R.string.loading
import com.example.news.adapters.AdapterLatestNews
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.utils.ApiResult
import com.example.news.utils.COUNTRY
import com.example.news.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private val adapterLatestNews = AdapterLatestNews()
    private val _viewModel : HomeViewModel by viewModels ()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?)
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
            showLoadingDialog(loading)
            when (latestNews) {
                is ApiResult.Failure -> {
                    showToast(latestNews.throwable.message.toString())
                    hideLoadingDialog()
                }

                is ApiResult.Success ->
                    latestNews.data.let {
                        if (it != null) {
                            hideLoadingDialog()
                            initViews(it)
                        }
                    }
            }
        }
    }

    private fun initViews(lNews: List<LNews>) {
        adapterLatestNews.lNewsList = lNews
        binding.rvLatestNews.adapter = adapterLatestNews
        adapterLatestNews.notifyDataSetChanged()
        adapterLatestNews.onItemClickListener?.onItemClick(lNews[0],0)
        binding.btnCategory.setOnClickListener {
            Toast.makeText(requireContext(), lNews.size.toString(), Toast.LENGTH_SHORT).show()

        }
    }
}