package com.example.news.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.models.LNews
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.base.BaseViewModel
import com.example.news.databinding.FragmentFavorietsBinding
import com.example.news.databinding.FragmentSearchBinding
import com.example.news.ui.search.adapters.AdapterSearch
import com.example.news.utils.showBottomNav
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    private val _viewModel : SearchViewModel by viewModels ()
    private val adapterSearch = AdapterSearch()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentSearchBinding.inflate(inflater,container,false)

    override fun initViewModel(): SearchViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initClicks()
    }

    override fun onResume() {
        super.onResume()
        showBottomNav()
    }

    private fun initClicks() {
        var searchJob : Job? = null
        binding.etSearch.doAfterTextChanged {
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(500)
                requestQuery()
            }
        }
        adapterSearch.setOnClick {
            findNavController().navigate(SearchFragmentDirections.actionGlobalToDetailsFragment(it))
        }
    }

    private fun observe() {
        _viewModel.lSearch.observe(viewLifecycleOwner){
            initViews(it)
        }
    }

    private fun requestQuery() {
        val searchQuery = binding.etSearch.text.toString()
        if (searchQuery.isNotEmpty()) {
            _viewModel.getSearchQuery(searchQuery)
        }
    }

    private fun initViews(lSearch : List<LNews>?) {
        adapterSearch.submitList(lSearch)
        binding.rvSearch.adapter=adapterSearch
    }
}