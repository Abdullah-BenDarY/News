package com.example.news.ui.hot_updates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.LNews
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentHotUpdatesBinding
import com.example.news.ui.hot_updates.adapters.AdapterHotUpdates
import com.example.news.utils.hideBottomNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotUpdatesFragment : BaseFragment<FragmentHotUpdatesBinding, HotUpdatesViewModel>() {
    private val adapterHotUpdates = AdapterHotUpdates()
    private val _viewModel: HotUpdatesViewModel by viewModels()
    private val args by navArgs<HotUpdatesFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHotUpdatesBinding.inflate(inflater, container, false)

    override fun initViewModel(): HotUpdatesViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNav()
        _viewModel.getLatestNews(args.newsSource.toString())
        initClicks()
        observe()
    }

    private fun initClicks() {
        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }
        adapterHotUpdates.setOnClick {
            findNavController().navigate(HotUpdatesFragmentDirections.actionGlobalToDetailsFragment(it))
        }
    }

    private fun observe() {
        _viewModel.lNews.observe(viewLifecycleOwner){
            setContentViews(it)
        }
    }

    private fun setContentViews(lNews : List<LNews>?) {
        adapterHotUpdates.submitList(lNews)
        binding.rvNews.adapter = adapterHotUpdates
    }
}