package com.example.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.domain.models.LNews
import com.example.domain.models.ModelNewsSource
import com.example.news.ModelTabs
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.ui.home.adapters.AdapterCategoriesTabs
import com.example.news.ui.home.adapters.AdapterLatestNews
import com.example.news.ui.home.adapters.AdapterNews
import com.example.news.ui.home.adapters.AdapterSourceTabs
import com.example.news.utils.ABC_NEWS
import com.example.news.utils.BUSINESS_CATEGORY
import com.example.news.utils.ENTERTAINMENT_CATEGORY
import com.example.news.utils.GENERAL_CATEGORY
import com.example.news.utils.HEALTH_CATEGORY
import com.example.news.utils.SCIENCE_CATEGORY
import com.example.news.utils.SPORT_CATEGORY
import com.example.news.utils.TECHNOLOGY_CATEGORY
import com.example.news.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private val adapterLatestNews = AdapterLatestNews()
    private val adapterCategories = AdapterCategoriesTabs()
    private val adapterSource = AdapterSourceTabs()
    private val adapterNews = AdapterNews()
    private val _viewModel: HomeViewModel by viewModels()
    private val tabsList = mutableListOf<ModelTabs>()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun initViewModel(): HomeViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelCalls()
        setupCategoryList()
        initClicks()
        observe()
    }

    private fun viewModelCalls() {
        _viewModel.getLatestNews(GENERAL_CATEGORY)
        _viewModel.getNewsSource(GENERAL_CATEGORY)
    }

    private fun initClicks() {
        binding.tvSeeAll.setOnClickListener {
            showToast(":)")
        }
        adapterCategories.setOnClick { category ->
            _viewModel.getLatestNews(category.title.toString())
            _viewModel.getNewsSource(category.title.toString())
        }
        adapterLatestNews.setOnClick {
            showToast(it.title)
        }
 feat-ui-home
        adapterSource.setOnClick { source ->
            _viewModel.getNewsBySource(source.id.toString())

        }

        adapterNews.setOnClick {
            showToast(it.title)
        
    }

    private fun observe() {
        _viewModel.lNews.observe(viewLifecycleOwner) { latestNews ->
            setLatestNewsAdapter(latestNews)
        }

        _viewModel.newsSource.observe(viewLifecycleOwner) { newsSource ->
            setNewsSourceAdapter(newsSource)
            _viewModel.getNewsBySource(newsSource!![0].id.toString())
        }

        _viewModel.newsBySource.observe(viewLifecycleOwner) { news ->
            setNewsAdapter(news)
        }

    }

    private fun setupCategoryList() {
        tabsList.add(ModelTabs(GENERAL_CATEGORY))
        tabsList.add(ModelTabs(TECHNOLOGY_CATEGORY))
        tabsList.add(ModelTabs(SCIENCE_CATEGORY))
        tabsList.add(ModelTabs(HEALTH_CATEGORY))
        tabsList.add(ModelTabs(SPORT_CATEGORY))
        tabsList.add(ModelTabs(ENTERTAINMENT_CATEGORY))
        tabsList.add(ModelTabs(BUSINESS_CATEGORY))
        adapterCategories.submitList(tabsList)
        binding.rvTabs.adapter = adapterCategories
    }

    private fun setLatestNewsAdapter(lNews: List<LNews>?) {
        adapterLatestNews.submitList(lNews)
        binding.rvLatestNews.adapter = adapterLatestNews
    }

    private fun setNewsSourceAdapter(newsSource: List<ModelNewsSource>?) {
        adapterSource.submitList(newsSource)
        binding.rvNewsSource.adapter = adapterSource
    }

    private fun setNewsAdapter(newsSource: List<LNews>?) {
        adapterNews.submitList(newsSource)
        binding.rvNews.adapter = adapterNews
    }
}